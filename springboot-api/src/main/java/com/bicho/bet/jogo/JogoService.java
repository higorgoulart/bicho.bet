package com.bicho.bet.jogo;

import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JogoService {
    private JogoRepository repository;

    public Page<Jogo> getAll(Pageable pageable) {
        return repository.findAll(QJogo.jogo.status.eq(StatusJogo.ABERTO), pageable);
    }

    public Jogo getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Jogo"));
    }
}
