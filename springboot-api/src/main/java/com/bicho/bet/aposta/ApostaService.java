package com.bicho.bet.aposta;

import com.bicho.bet.apostador.ApostadorService;
import com.bicho.bet.bicho.BichoService;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.exceptions.NotFoundException;
import com.bicho.bet.historico.HistoricoResponse;
import com.bicho.bet.jogo.JogoService;
import com.bicho.bet.resultado.TipoResultado;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ApostaService {
    private ModelMapper modelMapper;
    private ApostaRepository repository;
    private ApostadorService apostadorService;
    private BichoService bichoService;

    public List<Aposta> getAll() {
        return repository.findAll();
    }

    public Aposta getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Aposta"));
    }

    public Aposta add(ApostaRepresentation.ApostaCreate create, JogoService jogoService) {
        var apostador = apostadorService.getById(create.getApostador());
        var aposta = apostador.apostar(
                jogoService.getById(create.getJogo()),
                create.getValor(),
                create.getTipo(),
                create.getNumeros().stream().map(BetNumber::parseBetNumber).toList()
        );

        return repository.save(aposta);
    }

    public List<Aposta> obterApostasPorJogo(Long idJogo) {
        var apostas = repository.findAll(QAposta.aposta.jogo.id.eq(idJogo));

        if (apostas.isEmpty()) {
            throw new JogoSemApostaException();
        }

        return apostas;
    }

    public List<Aposta> obterPorApostador(Long idApostador) {
        var apostas = repository.findAll(QAposta.aposta.apostador.id.eq(idApostador));

        if (apostas.isEmpty()) {
            throw new JogoSemApostaException();
        }

        return apostas;
    }

    public Page<HistoricoResponse> findHistoricosByIdApostador(Long idApostador, Pageable pageable) {
        return repository.findHistoricosByIdApostador(idApostador, pageable);
    }

    public Double obterPremio(Double valor, TipoAposta tipo, List<BetNumber> apostas, List<BetNumber> resultados) {
        var posicoes = obterPosicoesCorretas(tipo, apostas, resultados);
        var multiplicador = Aposta.obterMultiplicador(tipo, posicoes);

        return valor * multiplicador;
    }

    private List<TipoResultado> obterPosicoesCorretas(
            TipoAposta tipo,
            List<BetNumber> apostas,
            List<BetNumber> resultados) {
        var posicoes = new ArrayList<TipoResultado>();

        int i = 0;

        for (var resultado : resultados) {
            for (var apostado : apostas) {
                boolean acerto;

                if (apostado.lessThan(100)) {
                    acerto = resultado.getDezena().equals(apostado);
                } else if (apostado.lessThan(1000)) {
                    acerto = resultado.getCentena().equals(apostado);
                } else {
                    acerto = resultado.equals(apostado);
                }

                if (acerto) {
                    int index = i + 1;
                    var tipoResultado = Arrays.stream(TipoResultado.values()).filter(x -> x.value == index).findFirst();
                    tipoResultado.ifPresent(posicoes::add);
                } else if (tipo == TipoAposta.DEZENA || tipo == TipoAposta.CENTENA || tipo == TipoAposta.MILHAR) {
                    var bichoApostado = bichoService.getByNumber(apostado);
                    var bichoResultado = bichoService.getByNumber(resultado.getDezena());

                    if (bichoApostado.equals(bichoResultado)) {
                        posicoes.add(TipoResultado.BICHO);
                    }
                }
            }

            i++;
        }

        return posicoes;
    }
}
