package com.bicho.bet.aposta;

import com.bicho.bet.apostador.ApostadorService;
import com.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.exceptions.NotFoundException;
import com.bicho.bet.jogo.JogoService;
import com.bicho.bet.resultado.NumeroResultado;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApostaService {
    private ModelMapper modelMapper;
    private ApostaRepository repository;
    private ApostadorService apostadorService;
    private JogoService jogoService;

    public List<Aposta> getAll() {
        return repository.findAll();
    }

    public Aposta getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Aposta"));
    }

    public Aposta add(ApostaRepresentation.ApostaCreateUpdate create) {
        return repository.save(Aposta.builder()
                .apostador(apostadorService.getById(create.getApostador()))
                .jogo(jogoService.getById(create.getJogo()))
                .valor(create.getValor())
                .data(create.getData())
                .tipo(create.getTipo())
//                .numeros(create.getNumeros())
                .build());
    }

    public Aposta update(Long id, ApostaRepresentation.ApostaCreateUpdate entity) {
        var dbEntity = repository.findById(id).orElseThrow(() -> new NotFoundException("Aposta"));

        modelMapper.map(entity, dbEntity);

        return repository.save(dbEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Double premiarVencedores(Long idJogo, List<NumeroResultado> resultados) throws JogoSemApostaException {
        var total = 0.0;

        for (Aposta aposta : obterApostasPorJogo(idJogo)) {
            var apostador = aposta.getApostador();

            var premio = aposta.obterPremio(resultados);

            apostador.depositar(premio);

            apostadorService.update(apostador.getId(), apostador);

            total += premio;
        }

        return total;
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
}
