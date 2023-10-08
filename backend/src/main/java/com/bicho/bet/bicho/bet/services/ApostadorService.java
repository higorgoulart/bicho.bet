package com.bicho.bet.bicho.bet.services;

import com.bicho.bet.bicho.bet.exceptions.SaqueInvalidoException;
import com.bicho.bet.bicho.bet.models.conta.Apostador;
import com.bicho.bet.bicho.bet.repositories.ApostaRepository;
import com.bicho.bet.bicho.bet.repositories.ApostadorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApostadorService extends AbstractService<Apostador, Long>{
    @Autowired
    private ApostaRepository apostaRepository;

    @Autowired
    private ApostadorRepository repository;

    @Override
    public ApostadorRepository getRepository() {
        return repository;
    }

    public Double sacar(Double valorSaque) throws SaqueInvalidoException {
        double valorSaldo = repository.getSaldo();
        double valorAposta = apostaRepository.getValor();
        if (valorSaque < valorAposta*3.00 || valorSaque > valorSaldo){
            throw new SaqueInvalidoException();
        }
        repository.setSaldo(valorSaldo - valorSaque);
        return valorSaque;
    }
}
