package com.bicho.bet.jogo;

import com.bicho.bet.exceptions.JogoEmExecucaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("jogos")
public class JogoController {
    @Autowired
    private JogoService service;

    @GetMapping
    public ResponseEntity<Page<JogoRepresentation.JogoResponse>> getAll(
            @RequestParam(required = false) Optional<Integer> p
    ) {
        Pageable page = PageRequest.of(p.orElse(0), 2, Sort.by("dataFim").descending());

        return ResponseEntity.ok(service.getAll(page).map(JogoRepresentation.JogoResponse::from));
    }

    @PostMapping("/lotericas/{idLoterica}")
    public Jogo abrirJogo(@PathVariable Long idLoterica) throws JogoEmExecucaoException {
        return service.abrirJogo(idLoterica);
    }
}