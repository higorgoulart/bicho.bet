package com.bicho.bet.jogo;

import com.bicho.bet.exceptions.JogoEmExecucaoException;
import com.bicho.bet.exceptions.NotFoundException;
import com.bicho.bet.loterica.LotericaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class JogoService {
    private JogoRepository repository;
    private LotericaService lotericaService;

    public Page<Jogo> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Jogo getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Jogo"));
    }

    public Jogo abrirJogo(Long idLoterica) throws JogoEmExecucaoException {
        if (repository.exists(QJogo.jogo.loterica.id.eq(idLoterica)
                .and(QJogo.jogo.status.eq(StatusJogo.ABERTO)))) {
            throw new JogoEmExecucaoException();
        }

        var jogo = new Jogo();
        jogo.setDataInicio(LocalDateTime.now());
        jogo.setLoterica(lotericaService.getById(idLoterica));
        jogo.setStatus(StatusJogo.ABERTO);

        return jogo;
    }
}
