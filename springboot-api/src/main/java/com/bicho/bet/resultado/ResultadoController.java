package com.bicho.bet.resultado;

import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("resultados")
@AllArgsConstructor
public class ResultadoController {
    private ResultadoService service;

    @GetMapping
    public ResponseEntity<List<ResultadoRepresentation.ResultadoResponse>> getAll(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Integer p
    ) {
        Pageable page = PageRequest.of(p, 2);

        return ResponseEntity.ok(service.getAll(filter, page).stream().map(ResultadoRepresentation.ResultadoResponse::from).toList());
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