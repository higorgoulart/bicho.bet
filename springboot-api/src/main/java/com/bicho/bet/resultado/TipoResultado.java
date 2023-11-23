package com.bicho.bet.resultado;

public enum TipoResultado {
    PRIMEIRA(1),
    SEGUNDA(2),
    TERCEIRA(3),
    QUARTA(4),
    QUINTA(5),
    BICHO(0);

    public final Integer value;

    TipoResultado(Integer val) {
        value = val;
    }
}