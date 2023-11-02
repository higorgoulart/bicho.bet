package com.bicho.bet.bicho.bet.aposta;

import com.bicho.bet.bicho.bet.core.EntityId;
import com.bicho.bet.bicho.bet.resultado.Resultado;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Historico extends EntityId {
    @ManyToOne
    @JoinColumn(name = "aposta_id")
    private Aposta aposta;

    @ManyToOne
    @JoinColumn(name = "resultado_id")
    private Resultado resultado;
}
