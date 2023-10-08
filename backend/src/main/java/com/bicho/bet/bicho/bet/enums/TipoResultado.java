package com.bicho.bet.bicho.bet.enums;

public enum TipoResultado {
    PRIMEIRA(1),
    SEGUNDA(2),
    TERCEIRA(3),
    QUARTA(4),
    QUINTA(5),
    BICHO(0);

    private final Integer value;

    TipoResultado(Integer val) {
        value = val;
    }
}