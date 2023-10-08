package com.bicho.bet.bicho.bet.services;

import java.util.List;

import com.bicho.bet.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.bicho.bet.models.aposta.QAposta;
import com.bicho.bet.bicho.bet.repositories.ApostadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bicho.bet.bicho.bet.models.aposta.Aposta;
import com.bicho.bet.bicho.bet.models.resultado.NumeroResultado;
import com.bicho.bet.bicho.bet.repositories.ApostaRepository;

@Service
public class ApostaService extends AbstractService<Aposta, Long> {
    @Autowired
    private ApostaRepository repository;

    @Autowired
    private ApostadorRepository apostadorRepository;

    @Override
    public ApostaRepository getRepository() {
        return repository;
    }

    public Double premiarVencedores(Long idJogo, List<NumeroResultado> resultados) throws JogoSemApostaException {
        var total = 0.0;

        for (Aposta aposta : obterApostasJogo(idJogo)) {
            var apostador = aposta.getApostador();

            var premio = aposta.obterPremio(resultados);

            apostador.depositar(premio);

            apostadorRepository.save(apostador);

            total += premio;
        }

        return total;
    }

    private List<Aposta> obterApostasJogo(Long idJogo) throws JogoSemApostaException {
        var apostas = repository.findAll(QAposta.aposta.jogo.id.eq(idJogo));

        if (apostas.isEmpty()) {
            throw new JogoSemApostaException();
        }

        return apostas;
    }
}
