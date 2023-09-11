package com.bicho.bet.bicho.bet.models.resultado;

import com.bicho.bet.bicho.bet.models.core.EntityId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NumeroResultado extends EntityId {
    @ManyToOne
    @JoinColumn(name = "resultado_id")
    private Resultado resultado;

    @Column(name = "numero")
    private Short numero;

    public NumeroResultado() {
    }

    public Short getNumero() {
        return numero;
    }
}
