package com.bicho.bet.resultado;

import com.bicho.bet.core.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResultadoService extends BaseService<Resultado, Long> {
    @Autowired
    private ResultadoRepository repository;

    @Override
    public ResultadoRepository getRepository() {
        return repository;
    }
}
