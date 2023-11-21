package com.bicho.bet.jogo;

import com.bicho.bet.aposta.ApostaService;
import com.bicho.bet.core.BaseService;
import com.bicho.bet.exceptions.JogoEmExecucaoException;
import com.bicho.bet.loterica.LotericaService;
import com.bicho.bet.resultado.ResultadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class JogoService extends BaseService<Jogo, Long> {
    private JogoRepository repository;

    private ResultadoRepository resultadoRepository;

    private LotericaService lotericaService;

    @Override
    public JogoRepository getRepository() {
        return repository;
    }

    private ApostaService apostaService;

    public List<Jogo> getAll() {
        return repository.findAll();
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
