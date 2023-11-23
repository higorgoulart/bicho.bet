package com.bicho.bet.apostador;

import com.bicho.bet.core.BaseController;
import com.bicho.bet.core.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apostadores")
public class ApostadorController extends BaseController<Apostador, Long> {
    @Autowired
    private ApostadorService service;

    @Override
    protected BaseService<Apostador, Long> getService() {
        return service;
    }
}