package com.bicho.bet.bicho;

import com.bicho.bet.core.BetNumber;
import com.bicho.bet.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BichoService {
    private BichoRepository repository;

    public Bicho getByNumber(BetNumber number) {
        var bichos = repository.findAll(QBicho.bicho.numeros.isNotEmpty());

        return bichos.stream()
                .filter(x -> x.getNumeros().contains(number)).findFirst()
                .orElseThrow(() -> new NotFoundException("Bicho"));
    }
}
