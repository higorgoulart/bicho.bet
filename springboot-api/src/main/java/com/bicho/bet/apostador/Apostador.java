package com.bicho.bet.apostador;

import com.bicho.bet.conta.Conta;
import com.bicho.bet.aposta.TipoAposta;
import com.bicho.bet.exceptions.ContaSemCreditoException;
import com.bicho.bet.exceptions.ContaSemSaldoException;
import com.bicho.bet.aposta.Aposta;
import com.bicho.bet.aposta.NumeroAposta;
import com.bicho.bet.jogo.Jogo;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Apostador extends Conta {
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "divida")
    private Double divida;

    @Column(name = "limite")
    private Double limite;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getDivida() {
        return divida;
    }

    public void setDivida(Double divida) {
        this.divida = divida;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Long apostar(Jogo jogo, Double valor, TipoAposta tipo, List<NumeroAposta> numeros) throws ContaSemSaldoException {
        if (valor > this.getSaldo()) {
            throw new ContaSemSaldoException();
        }

        Aposta palpite = new Aposta(this, jogo, valor, LocalDateTime.now(), tipo, numeros);
        return palpite.getId();
    }

    public void solicitarEmprestimo(double valorDesejado) throws ContaSemCreditoException {
        if (valorDesejado > this.getLimite()) {
            throw new ContaSemCreditoException();
        }
        double juros = 0.2;
        double valorComJuros = valorDesejado * (1 + juros);
        this.setSaldo(getSaldo() + valorDesejado);
        this.setDivida(getDivida() + valorComJuros);
    }
}
