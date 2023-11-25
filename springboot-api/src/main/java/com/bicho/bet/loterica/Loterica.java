package com.bicho.bet.loterica;

import com.bicho.bet.conta.Conta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Loterica extends Conta {
    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "telefone")
    private String telefone;
}
