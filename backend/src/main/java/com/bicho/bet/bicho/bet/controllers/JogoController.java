package com.bicho.bet.bicho.bet.controllers;

import com.bicho.bet.bicho.bet.exceptions.JogoEmExecucaoException;
import com.bicho.bet.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.bicho.bet.models.aposta.Aposta;
import com.bicho.bet.bicho.bet.models.conta.Loterica;
import com.bicho.bet.bicho.bet.models.jogo.Jogo;
import com.bicho.bet.bicho.bet.services.ApostaService;
import com.bicho.bet.bicho.bet.services.BaseService;
import com.bicho.bet.bicho.bet.services.JogoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController extends BaseController<Jogo, Long> {
    @Autowired
    private JogoService service;

    @Override
    protected BaseService<Jogo, Long> getService() {
        return service;
    }

    @PostMapping("/lotericas/{idLoterica}")
    public Jogo abrirJogo(@PathVariable Long idLoterica) throws JogoEmExecucaoException {
        return service.abrirJogo(idLoterica);
    }

    @SneakyThrows
    @PostMapping
    @Override
    public Jogo add(@RequestBody Jogo jogo) {
        return service.fecharJogo(jogo);
    }
}