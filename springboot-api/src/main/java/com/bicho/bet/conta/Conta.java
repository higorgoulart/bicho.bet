package com.bicho.bet.conta;

import com.bicho.bet.core.EntityId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Conta extends EntityId {
    @Column(name = "nome")
    private String nome;

    @Column(name = "saldo")
    private Double saldo;

    public void depositar(double valor) {
        this.saldo += valor;
    }
}
