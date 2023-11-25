package com.bicho.bet.loterica;


import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("lotericas")
@AllArgsConstructor
public class LotericaController {
    @Autowired
    private LotericaService service;

    @GetMapping
    public ResponseEntity<List<LotericaRepresentation.LotericaResponse>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(LotericaRepresentation.LotericaResponse::from).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<LotericaRepresentation.LotericaResponse> getById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(LotericaRepresentation.LotericaResponse.from(service.getById(id)));
        } catch (NotFoundException ex){
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<LotericaRepresentation.LotericaResponse> update(
            @PathVariable Long id, @RequestBody LotericaRepresentation.LotericaUpdate entity){
        try{
          return ResponseEntity.ok().body(LotericaRepresentation.LotericaResponse.from(service.update(id, entity)));
        } catch (NotFoundException ex){
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<LotericaRepresentation.LotericaResponse> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}