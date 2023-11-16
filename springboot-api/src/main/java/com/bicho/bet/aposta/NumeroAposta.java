package com.bicho.bet.aposta;

import com.bicho.bet.core.EntityId;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NumeroAposta extends EntityId {
    @ManyToOne
    @JoinColumn(name = "aposta_id")
    private Aposta aposta;

    @Getter
    @Column(name = "numero")
    private Short numero;

}
