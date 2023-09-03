package com.bicho.bet.bicho.bet.models;

import java.time.LocalDateTime;

public class Loterica extends Conta {
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long gerarJogo(LocalDateTime dataInicial, LocalDateTime dataFim) {
        Jogo jogo = new Jogo(getId(), dataInicial, dataFim);
        return jogo.getId();
    }
}
