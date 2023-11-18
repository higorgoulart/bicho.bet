package com.bicho.bet.aposta;

import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("apostas")
@AllArgsConstructor
public class ApostaController {
    private ApostaService service;

    @GetMapping
    public ResponseEntity<List<ApostaRepresentation.ApostaResponse>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(ApostaRepresentation.ApostaResponse::from).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApostaRepresentation.ApostaResponse> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ApostaRepresentation.ApostaResponse.from(service.getById(id)));
        } catch (NotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApostaRepresentation.ApostaResponse> add(
            @RequestBody ApostaRepresentation.ApostaCreateUpdate entity) {
        var save = service.add(entity);

        return ResponseEntity
                .created(URI.create("/" + save.getId()))
                .body(ApostaRepresentation.ApostaResponse.from(save));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApostaRepresentation.ApostaResponse> update(
            @PathVariable Long id, @RequestBody ApostaRepresentation.ApostaCreateUpdate entity) {
        try {
            return ResponseEntity.ok().body(ApostaRepresentation.ApostaResponse.from(service.update(id, entity)));
        } catch (NotFoundException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApostaRepresentation.ApostaResponse> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("jogos/{idJogo}")
    public List<Aposta> obterPorJogo(@PathVariable Long idJogo) {
        return service.obterApostasPorJogo(idJogo);
    }

    @GetMapping("apostadores/{idApostador}")
    public List<Aposta> obterPorApostador(@PathVariable Long idApostador) {
        return service.obterPorApostador(idApostador);
    }
}