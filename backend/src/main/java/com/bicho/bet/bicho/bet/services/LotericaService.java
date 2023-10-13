package com.bicho.bet.bicho.bet.services;

import com.bicho.bet.bicho.bet.models.conta.Loterica;
import com.bicho.bet.bicho.bet.repositories.LotericaRepository;
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
