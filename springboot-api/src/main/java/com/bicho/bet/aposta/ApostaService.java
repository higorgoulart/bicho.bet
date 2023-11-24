package com.bicho.bet.aposta;

import com.bicho.bet.apostador.ApostadorService;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.exceptions.NotFoundException;
import com.bicho.bet.historico.HistoricoResponse;
import com.bicho.bet.jogo.JogoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApostaService {
    private ApostaRepository repository;
    private ApostadorService apostadorService;

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
}
