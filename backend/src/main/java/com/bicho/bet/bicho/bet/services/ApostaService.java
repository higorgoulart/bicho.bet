package com.bicho.bet.bicho.bet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.bicho.bet.bicho.bet.models.aposta.Aposta;
import com.bicho.bet.bicho.bet.models.resultado.NumeroResultado;
import com.bicho.bet.bicho.bet.repositories.ApostaRepository;

@Service
public class ApostaService extends AbstractService<Aposta, Long> {
    @Autowired
    private ApostaRepository repository;

    @Override
    public ApostaRepository getRepository() {
        return repository;
    }

    public void premiarVencedores(List<NumeroResultado> numeros) {
        obterApostasCorretasPosicao(numeros)
            .forEach(pair -> {
                var posicoes = pair.getFirst();
                var aposta = pair.getSecond();

                var apostador = aposta.getApostador();
                var multiplicador = aposta.obterMultiplicador(posicoes);

                apostador.setSaldo(apostador.getSaldo() + (aposta.getValor() * multiplicador));

                // TODO: fazer saves
            });
    }

    private List<Pair<List<Integer>, Aposta>> obterApostasCorretasPosicao(List<NumeroResultado> numeros) {
        // TODO: fazer select que retorne apostas com sucesso e posição
        return null;
    }
}
