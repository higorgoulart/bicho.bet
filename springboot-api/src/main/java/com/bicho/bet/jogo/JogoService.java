package com.bicho.bet.jogo;

import java.time.LocalDateTime;

import com.bicho.bet.aposta.ApostaService;
import com.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.exceptions.JogoEmExecucaoException;
import com.bicho.bet.jogo.QJogo;
import com.bicho.bet.resultado.ResultadoRepository;
import com.bicho.bet.core.BaseService;
import com.bicho.bet.loterica.LotericaService;
import com.bicho.bet.resultado.Resultado;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JogoService extends BaseService<Jogo, Long> {
    @Autowired
    private JogoRepository repository;

    @Autowired
    private LotericaService lotericaService;

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Override
    public JogoRepository getRepository() {
        return repository;
    }

    @Autowired
    private ApostaService apostaService;

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

    public Jogo fecharJogo(Jogo jogo) throws JogoSemApostaException {
        if (!repository.exists(QJogo.jogo.id.eq(jogo.getId())
                .and(QJogo.jogo.status.eq(StatusJogo.ABERTO)))) {
            throw new IllegalArgumentException("O jogo informado não está em execução.");
        }

        jogo.setDataFim(LocalDateTime.now());
        jogo.setStatus(StatusJogo.FECHADO);

        var resultado = new Resultado(jogo);
        var numeros = resultado.getNumeroResultados();

        resultadoRepository.save(resultado);

        var totalPremiado = apostaService.premiarVencedores(jogo.getId(), numeros);
        var lucroLoterica = jogo.getValorAcumulado() - totalPremiado;

        var loterica = jogo.getLoterica();
        loterica.depositar(lucroLoterica);
        lotericaService.update(loterica.getId(), loterica);
        return update(jogo.getId(), jogo);
    }
}
