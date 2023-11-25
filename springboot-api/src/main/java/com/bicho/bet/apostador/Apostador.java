package com.bicho.bet.apostador;

import com.bicho.bet.conta.Conta;
import com.bicho.bet.aposta.TipoAposta;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.exceptions.ContaSemCreditoException;
import com.bicho.bet.aposta.Aposta;
import com.bicho.bet.exceptions.ContaSemSaldoException;
import com.bicho.bet.exceptions.SaqueInvalidoException;
import com.bicho.bet.jogo.Jogo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Apostador extends Conta {
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "divida")
    private Double divida;

    @Column(name = "limite")
    private Double limite;

    public Apostador(String nome, String telefone, String cpf, Double saldo, Double depositado, Double divida, Double limite) {
        super(nome, telefone, saldo, depositado);
        this.cpf = cpf;
        this.divida = divida;
        this.limite = limite;
    }

    public void sacar(double valor) {
        if (valor > getSaldo() || (getDepositado() * 3) < valor)
            throw new SaqueInvalidoException();

        setSaldo(getSaldo() - valor);
        setDepositado(getDepositado() - valor);
    }

    public Aposta apostar(Jogo jogo, Double valor, TipoAposta tipo, List<BetNumber> numeros) {
        if (valor > (this.getSaldo() + (this.getLimite() - this.getDivida()))) {
            throw new ContaSemSaldoException();
        }

        if (this.getSaldo() >= valor) {
            this.setSaldo(this.getSaldo() - valor);
        } else {
            solicitarEmprestimoMinimo(valor);
        }

        return new Aposta(this, jogo, valor, LocalDateTime.now(), tipo, numeros);
    }

    public void solicitarEmprestimo(double valorDesejado) {
        if (valorDesejado > (this.getLimite() - this.getDivida())) {
            throw new ContaSemCreditoException();
        }

        this.setDivida(getDivida() + obterJuros(valorDesejado));
        this.setSaldo(getSaldo() + valorDesejado);
    }

    private void solicitarEmprestimoMinimo(double valorDesejado) {
        if (this.getSaldo() > 0) {
            valorDesejado -= this.getSaldo();
        }

        this.setDivida(this.getDivida() + obterJuros(valorDesejado));
        this.setSaldo(0.0);
    }

    private double obterJuros(double valor) {
        double juros = 0.2;
        return valor * (1 + juros);
    }
}
