package com.bicho.bet.exceptions;

public class DividaMaiorQueDepositoException extends RuntimeException{

    public DividaMaiorQueDepositoException() {super("Sua dívida é maior que seu depósito!");}
}
