package com.bicho.bet.bicho.bet.controllers;

import com.bicho.bet.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.bicho.bet.models.aposta.Aposta;
import com.bicho.bet.bicho.bet.services.ApostaService;
import com.bicho.bet.bicho.bet.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apostas")
public class ApostaController extends BaseController<Aposta, Long> {
    @Autowired
    private ApostaService service;

    @Override
    protected BaseService<Aposta, Long> getService() {
        return service;
    }

    @GetMapping("/jogos/{idJogo}")
    public List<Aposta> obterPorJogo(@PathVariable Long idJogo) throws JogoSemApostaException {
        return service.obterApostasJogo(idJogo);
    }
}