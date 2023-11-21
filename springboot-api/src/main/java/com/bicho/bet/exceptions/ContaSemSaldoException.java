package com.bicho.bet.exceptions;

public class ContaSemSaldoException extends RuntimeException {
    public ContaSemSaldoException() {
        super("A conta não possui saldo suficiente para realizar a aposta!");
    }
}
