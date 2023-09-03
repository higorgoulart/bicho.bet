package com.bicho.bet.bicho.bet.models.conta;

import com.bicho.bet.bicho.bet.enums.TipoAposta;
import com.bicho.bet.bicho.bet.models.aposta.Aposta;
import com.bicho.bet.bicho.bet.models.aposta.NumeroAposta;

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

    public Long apostar(Long idJogo, Double valor, TipoAposta tipo, List<NumeroAposta> numeros) {
        Aposta palpite = new Aposta(getId(), idJogo, valor, LocalDateTime.now(), tipo, numeros);
        return palpite.getId();
    }
}
