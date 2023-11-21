package com.bicho.bet.aposta;

import com.bicho.bet.exceptions.NotFoundException;
import com.bicho.bet.jogo.JogoService;
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
    private JogoService jogoService;

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
            @RequestBody ApostaRepresentation.ApostaCreate entity) {
        var save = service.add(entity, jogoService);

        return ResponseEntity
                .created(URI.create("/" + save.getId()))
                .body(ApostaRepresentation.ApostaResponse.from(save));
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