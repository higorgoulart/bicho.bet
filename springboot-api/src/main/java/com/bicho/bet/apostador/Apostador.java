package com.bicho.bet.apostador;

import com.bicho.bet.conta.Conta;
import com.bicho.bet.aposta.TipoAposta;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.exceptions.ContaSemCreditoException;
import com.bicho.bet.exceptions.ContaSemSaldoException;
import com.bicho.bet.aposta.Aposta;
import com.bicho.bet.exceptions.SaqueInvalidoException;
import com.bicho.bet.jogo.Jogo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Apostador extends Conta {
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "divida")
    private Double divida;

    @Column(name = "limite")
    private Double limite;

    public void sacar(double valor) {
        if (valor > getSaldo() || (getDepositado() * 3) < valor)
            throw new SaqueInvalidoException();

        setSaldo(getSaldo() - valor);
        setDepositado(getDepositado() - valor);
    }

    public Aposta apostar(Jogo jogo, Double valor, TipoAposta tipo, List<BetNumber> numeros) {
        if (valor > this.getSaldo()) {
            throw new ContaSemSaldoException();
        }

        return new Aposta(this, jogo, valor, LocalDateTime.now(), tipo, numeros);
    }

    public void solicitarEmprestimo(double valorDesejado) {
        if (valorDesejado > this.getLimite()) {
            throw new ContaSemCreditoException();
        }

        double juros = 0.2;
        double valorComJuros = valorDesejado * (1 + juros);
        this.setSaldo(getSaldo() + valorDesejado);
        this.setDivida(getDivida() + valorComJuros);
    }
}
