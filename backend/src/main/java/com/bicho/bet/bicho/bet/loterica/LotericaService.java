package com.bicho.bet.bicho.bet.loterica;

import com.bicho.bet.bicho.bet.core.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotericaService extends BaseService<Loterica, Long> {
    @Autowired
    private LotericaRepository repository;

    @Override
    public LotericaRepository getRepository() {
        return repository;
    }
}
