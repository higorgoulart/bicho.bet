package com.bicho.bet.loterica;

import com.bicho.bet.conta.Conta;
import com.bicho.bet.jogo.Jogo;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Loterica extends Conta {
    @Column(name = "cnpj")
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long gerarJogo(LocalDateTime dataInicial, LocalDateTime dataFim) {
        Jogo jogo = new Jogo(this, dataInicial, dataFim);
        return jogo.getId();
    }
}
