package com.bicho.bet.bicho.bet.aposta;

import java.util.List;

import com.bicho.bet.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.bicho.bet.aposta.QAposta;
import com.bicho.bet.bicho.bet.apostador.ApostadorRepository;
import com.bicho.bet.bicho.bet.core.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bicho.bet.bicho.bet.resultado.NumeroResultado;

@Service
public class ApostaService extends BaseService<Aposta, Long> {
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

    public List<Aposta> obterApostasJogo(Long idJogo) {
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
