package com.bicho.bet.conta;

import com.bicho.bet.core.EntityId;
import com.bicho.bet.security.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Conta extends User {

    @Column(name = "saldo")
    private double saldo;

    @Column(name = "depositado")
    private double depositado;

    public void depositar(double valor) {
        this.depositado += valor;
        this.saldo += valor;
    }
}
