package com.bicho.bet.conta;

import com.bicho.bet.core.EntityId;
import com.bicho.bet.security.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Conta extends User {

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "depositado")
    private Double depositado;

    public void depositar(double valor) {
        this.depositado += valor;
        this.saldo += valor;
    }
}
