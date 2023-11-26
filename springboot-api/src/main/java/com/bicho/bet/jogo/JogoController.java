package com.bicho.bet.jogo;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("jogos")
@AllArgsConstructor
public class JogoController {
    private JogoService service;

    @GetMapping
    public ResponseEntity<Page<JogoRepresentation.JogoResponse>> getAll(
            @RequestParam(required = false) Optional<Integer> p
    ) {
        Pageable page = PageRequest.of(p.orElse(0), 2, Sort.by("dataFim").descending());

        return ResponseEntity.ok(service.getAll(page).map(JogoRepresentation.JogoResponse::from));
    }
}