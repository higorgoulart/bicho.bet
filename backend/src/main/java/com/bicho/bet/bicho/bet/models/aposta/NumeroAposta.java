package com.bicho.bet.bicho.bet.models.aposta;

import com.bicho.bet.bicho.bet.models.core.EntityId;

public class NumeroAposta extends EntityId {
    private Long idAposta;
    private Short numero;

    public Long getIdAposta() {
        return idAposta;
    }

    public Short getNumero() {
        return numero;
    }
}