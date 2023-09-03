package com.bicho.bet.bicho.bet.models;

import com.bicho.bet.bicho.bet.enums.TipoAposta;

import java.time.LocalDateTime;
import java.util.List;

public class Apostador extends Conta {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long apostar(Long idJogo, Double valor, LocalDateTime data, TipoAposta tipo, List<NumeroAposta> numeros) {
        Aposta palpite = new Aposta(getId(), idJogo, valor, data, tipo, numeros);
        return palpite.getId();
    }
}
