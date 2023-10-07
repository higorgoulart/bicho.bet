package com.bicho.bet.bicho.bet.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bicho.bet.bicho.bet.models.jogo.Jogo;
import com.bicho.bet.bicho.bet.models.resultado.Resultado;
import com.bicho.bet.bicho.bet.repositories.JogoRepository;

@Service
public class JogoService {
    @Autowired
    private JogoRepository repository;

    @Autowired
    private ApostaService apostaService;

    public Jogo abrirJogo() {
        var jogo = new Jogo();

        jogo.setDataInicio(LocalDateTime.now());

        return jogo;

        // TODO: fazer saves
    }

    public void fecharJogo(Jogo jogo) {
        jogo.setDataFim(LocalDateTime.now());

        var resultado = new Resultado(jogo);
        var numeros = resultado.getNumeroResultados();
        apostaService.premiarVencedores(numeros);

        // TODO: fazer saves
    }
}
