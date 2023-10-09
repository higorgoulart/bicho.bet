package com.bicho.bet.bicho.bet.services;

import java.time.LocalDateTime;

import com.bicho.bet.bicho.bet.exceptions.JogoSemApostaException;
import com.bicho.bet.bicho.bet.exceptions.JogoEmExecucaoException;
import com.bicho.bet.bicho.bet.repositories.LotericaRepository;
import com.bicho.bet.bicho.bet.repositories.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bicho.bet.bicho.bet.models.jogo.Jogo;
import com.bicho.bet.bicho.bet.models.resultado.Resultado;
import com.bicho.bet.bicho.bet.repositories.JogoRepository;

@Service
public class JogoService extends AbstractService<Jogo, Long> {
    @Autowired
    private JogoRepository repository;

    @Autowired
    private LotericaRepository lotericaRepository;

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Override
    public JogoRepository getRepository() {
        return repository;
    }

    @Autowired
    private ApostaService apostaService;

    private Jogo jogoEmExecucao;

    public Jogo abrirJogo() throws JogoEmExecucaoException {
        if (jogoEmExecucao != null) {
            throw new JogoEmExecucaoException();
        }

        var jogo = new Jogo();
        jogo.setDataInicio(LocalDateTime.now());

        jogoEmExecucao = jogo;

        return jogo;
    }

    public void fecharJogo(Jogo jogo) throws JogoSemApostaException {
        if (jogo != jogoEmExecucao) {
            throw new IllegalArgumentException("O jogo informado não está em execução.");
        }

        jogo.setDataFim(LocalDateTime.now());

        var resultado = new Resultado(jogo);
        var numeros = resultado.getNumeroResultados();

        resultadoRepository.save(resultado);

        var totalPremiado = apostaService.premiarVencedores(jogo.getId(), numeros);
        var lucroLoterica = jogo.getValorAcumulado() - totalPremiado;

        var loterica = jogo.getLoterica();
        loterica.depositar(lucroLoterica);
        lotericaRepository.save(loterica);

        jogoEmExecucao = null;
    }
}
