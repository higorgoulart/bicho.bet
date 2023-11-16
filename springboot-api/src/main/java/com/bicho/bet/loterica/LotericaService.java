package com.bicho.bet.loterica;

import com.bicho.bet.core.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LotericaService extends BaseService<Loterica, Long> {
    @Autowired
    private LotericaRepository repository;

    @Override
    public LotericaRepository getRepository() {
        return repository;
    }
}
