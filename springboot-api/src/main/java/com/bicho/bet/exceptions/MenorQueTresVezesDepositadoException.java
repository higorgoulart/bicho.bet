package com.bicho.bet.exceptions;

public class MenorQueTresVezesDepositadoException extends RuntimeException {
    public MenorQueTresVezesDepositadoException() {
        super("Valor menor que 3 vezes a quantidade depositada. Ganhe para sacar!");
    }
}
