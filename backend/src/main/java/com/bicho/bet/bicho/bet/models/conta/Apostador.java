package com.bicho.bet.bicho.bet.models.conta;

import com.bicho.bet.bicho.bet.enums.TipoAposta;
import com.bicho.bet.bicho.bet.models.aposta.Aposta;
import com.bicho.bet.bicho.bet.models.aposta.NumeroAposta;
import com.bicho.bet.bicho.bet.models.jogo.Jogo;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Apostador extends Conta {
    @Column(name = "cpf")
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long apostar(Jogo jogo, Double valor, TipoAposta tipo, List<NumeroAposta> numeros) {
        Aposta palpite = new Aposta(this, jogo, valor, LocalDateTime.now(), tipo, numeros);
        return palpite.getId();
    }
}
