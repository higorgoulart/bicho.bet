package com.bicho.bet.bicho.bet.models.aposta;

import com.bicho.bet.bicho.bet.models.core.EntityId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NumeroAposta extends EntityId {
    @ManyToOne
    @JoinColumn(name = "aposta_id")
    private Aposta aposta;

    @Column(name = "numero")
    private Short numero;

    public Short getNumero() {
        return numero;
    }
}
