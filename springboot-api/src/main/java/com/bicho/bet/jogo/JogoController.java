package com.bicho.bet.jogo;

import com.bicho.bet.core.BaseController;
import com.bicho.bet.exceptions.JogoEmExecucaoException;
import com.bicho.bet.core.BaseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jogos")
public class JogoController extends BaseController<Jogo, Long> {
    @Autowired
    private JogoService service;

    @Override
    protected BaseService<Jogo, Long> getService() {
        return service;
    }

    @PostMapping("/lotericas/{idLoterica}")
    public Jogo abrirJogo(@PathVariable Long idLoterica) throws JogoEmExecucaoException {
        return service.abrirJogo(idLoterica);
    }

    @SneakyThrows
    @PostMapping
    @Override
    public Jogo add(@RequestBody Jogo jogo) {
        return service.fecharJogo(jogo);
    }
}