package com.bicho.bet.bicho.bet.exceptions;

public class ContaSemSaldoException extends Exception{
    public ContaSemSaldoException() {
        super("A conta não possui saldo suficiente para realizar a aposta!");
    }
}
