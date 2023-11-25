package com.bicho.bet.apostador;

import com.bicho.bet.conta.Conta;
import com.bicho.bet.aposta.TipoAposta;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.exceptions.ContaSemCreditoException;
import com.bicho.bet.aposta.Aposta;
import com.bicho.bet.exceptions.ContaSemSaldoException;
import com.bicho.bet.exceptions.SaqueInvalidoException;
import com.bicho.bet.jogo.Jogo;
import com.bicho.bet.security.role.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "apostador",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Apostador extends Conta {
    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "divida")
    private Double divida;

    @Column(name = "limite")
    private Double limite;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "depositado")
    private Double depositado;

    public Apostador(String nome, String username, String email, String password, String cpf, Double saldo) {
        super(nome, saldo);
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    public Apostador(String nome, String username, String email, String password, String cpf, Double saldo, Double depositado, Double divida, Double limite) {
        this(nome, username, email, password, cpf, saldo);
        this.divida = divida;
        this.limite = limite;
        this.depositado = depositado;
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
