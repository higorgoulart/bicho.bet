package com.bicho.bet.resultado;

import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("resultados")
@AllArgsConstructor
public class ResultadoController {
    private ResultadoService service;

    @GetMapping
    public ResponseEntity<Page<ResultadoRepresentation.ResultadoResponse>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Optional<Integer> p
    ) {
        Pageable page = PageRequest.of(p.orElse(0), 2, Sort.by("data").descending());

        return ResponseEntity.ok(service.getAll(filter, page).map(ResultadoRepresentation.ResultadoResponse::from));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoRepresentation.ResultadoResponse> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ResultadoRepresentation.ResultadoResponse.from(service.getById(id)));
        } catch (NotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<ResultadoRepresentation.ResultadoResponse> add(
            @RequestBody ResultadoRepresentation.ResultadoCreate entity) {
        var save = service.add(entity);

        return ResponseEntity
                .created(URI.create("/" + save.getId()))
                .body(ResultadoRepresentation.ResultadoResponse.from(save));
    }
}