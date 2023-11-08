package com.bicho.bet.bicho.bet.resultado;

import com.bicho.bet.bicho.bet.core.BaseController;
import com.bicho.bet.bicho.bet.core.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resultados")
public class ResultadoController extends BaseController<Resultado, Long> {
    @Autowired
    private ResultadoService service;

    @Override
    protected BaseService<Resultado, Long> getService() {
        return service;
    }
}