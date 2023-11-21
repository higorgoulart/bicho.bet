package com.bicho.bet.exceptions;

public class ContaSemCreditoException extends RuntimeException {
    public ContaSemCreditoException() {
        super("A conta não possui limite de crédito suficiente para realizar o empréstimo!");
    }
}
