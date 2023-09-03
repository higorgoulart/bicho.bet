package com.bicho.bet.bicho.bet.models.resultado;

import com.bicho.bet.bicho.bet.models.core.EntityId;

public class NumeroResultado extends EntityId {
    private Long idResultado;
    private Short numero;

    public Long getIdResultado() {
        return idResultado;
    }

    public Short getNumero() {
        return numero;
    }
}
