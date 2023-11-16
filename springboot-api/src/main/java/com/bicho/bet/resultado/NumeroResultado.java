package com.bicho.bet.resultado;

import com.bicho.bet.core.EntityId;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NumeroResultado extends EntityId {
    @ManyToOne
    @JoinColumn(name = "resultado_id")
    private Resultado resultado;

    @Getter
    @Column(name = "numero")
    private Short numero;

    public NumeroResultado() {
    }

    public NumeroResultado(Resultado resultado, Short numero) {
        this.resultado = resultado;
        this.numero = numero;
    }

    public Short getDezena() {
        return Short.parseShort(resultado.toString().substring(2));
    }

    public Short getCentena() {
        return Short.parseShort(resultado.toString().substring(1));
    }
}
