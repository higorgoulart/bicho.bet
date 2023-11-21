package com.bicho.bet.apostador;

import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("apostadores")
@AllArgsConstructor
public class ApostadorController {
    private ApostadorService service;

    @GetMapping
    public ResponseEntity<List<ApostadorRepresentation.ApostadorResponse>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(ApostadorRepresentation.ApostadorResponse::from).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApostadorRepresentation.ApostadorResponse> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ApostadorRepresentation.ApostadorResponse.from(service.getById(id)));
        } catch (NotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApostadorRepresentation.ApostadorResponse> add(
            @RequestBody ApostadorRepresentation.ApostadorCreateUpdate entity) {
        var save = service.add(entity);

        return ResponseEntity
                .created(URI.create("/" + save.getId()))
                .body(ApostadorRepresentation.ApostadorResponse.from(save));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApostadorRepresentation.ApostadorResponse> update(
            @PathVariable Long id, @RequestBody ApostadorRepresentation.ApostadorCreateUpdate entity) {
        try {
            return ResponseEntity.ok().body(ApostadorRepresentation.ApostadorResponse.from(service.update(id, entity)));
        } catch (NotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApostadorRepresentation.ApostadorResponse> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}