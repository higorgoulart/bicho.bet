package com.bicho.bet.loterica;

import com.bicho.bet.core.BaseController;
import com.bicho.bet.core.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lotericas")
public class LotericaController extends BaseController<Loterica, Long> {
    @Autowired
    private LotericaService service;

    @Override
    protected BaseService<Loterica, Long> getService() {
        return service;
    }
}