package com.bicho.bet.conta;

import com.bicho.bet.core.EntityId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Conta extends EntityId {
    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "saldo")
    private double saldo;

    @Column(name = "depositado")
    private double depositado;

    public void depositar(double valor) {
        this.depositado += valor;
        this.saldo += valor;
    }
}
