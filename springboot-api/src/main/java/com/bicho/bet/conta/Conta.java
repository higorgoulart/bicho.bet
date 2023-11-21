package com.bicho.bet.conta;

import com.bicho.bet.core.EntityId;
import lombok.*;

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

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "depositado")
    private Double depositado;

    public void depositar(double valor) {
        this.depositado += valor;
        this.saldo += valor;
    }
}
