package com.bicho.bet.apostador;

import com.bicho.bet.aposta.TipoAposta;
import com.bicho.bet.core.BaseService;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.exceptions.NotFoundException;
import com.bicho.bet.jogo.Jogo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApostadorService extends BaseService<Apostador, Long> {
    private ApostadorRepository repository;

    @Override
    public ApostadorRepository getRepository() {
        return repository;
    }

    public void sacar(Long id, double valor) {
        var apostador = repository.findById(id).orElseThrow(() -> new NotFoundException("Apostador"));

        apostador.sacar(valor);

        repository.save(apostador);
    }

    public void depositar(Long id, double valor) {
        var apostador = repository.findById(id).orElseThrow(() -> new NotFoundException("Apostador"));

        apostador.depositar(valor);

        repository.save(apostador);
    }

    public void apostar(Long id, Jogo jogo, Double valor, TipoAposta tipo, List<BetNumber> numeros) {
        var apostador = repository.findById(id).orElseThrow(() -> new NotFoundException("Apostador"));

        apostador.apostar(jogo, valor, tipo, numeros);

        repository.save(apostador);
    }
}
