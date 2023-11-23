package com.bicho.bet.historico;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("apostadores/{idApostador}/historicos")
@AllArgsConstructor
public class HistoricoController {
    private HistoricoService service;

    @GetMapping
    public ResponseEntity<Page<HistoricoResponse>> getAll(
            @PathVariable Long idApostador,
            @RequestParam(required = false) Optional<Integer> p) {
        Pageable page = PageRequest.of(p.orElse(0), 2, Sort.by("data").descending());

        return ResponseEntity.ok(service.getAll(idApostador, page));
    }
}