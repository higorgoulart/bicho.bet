package com.bicho.bet.jogo;

import com.bicho.bet.core.BaseController;
import com.bicho.bet.exceptions.JogoEmExecucaoException;
import com.bicho.bet.core.BaseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jogos")
public class JogoController {
    @Autowired
    private JogoService service;


    @GetMapping
    public ResponseEntity<List<JogoRepresentation.JogoResponse>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(JogoRepresentation.JogoResponse::from).toList());
    }

    @PostMapping("/lotericas/{idLoterica}")
    public Jogo abrirJogo(@PathVariable Long idLoterica) throws JogoEmExecucaoException {
        return service.abrirJogo(idLoterica);
    }
//
//    @SneakyThrows
//    @PostMapping
//    public Jogo add(@RequestBody Jogo jogo) {
//        return service.fecharJogo(jogo);
//    }
}