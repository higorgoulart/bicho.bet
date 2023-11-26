package com.bicho.bet.exceptions;

public class MenorQueTresVezesDepositadoException extends RuntimeException {
    public MenorQueTresVezesDepositadoException() {
        super("Saldo menor que 3 vezes o valor depositado. Ganhe para sacar!");
    }
}
