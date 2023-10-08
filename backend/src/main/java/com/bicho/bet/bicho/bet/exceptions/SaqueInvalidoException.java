package com.bicho.bet.bicho.bet.exceptions;

public class SaqueInvalidoException extends Exception{
    public SaqueInvalidoException() {
        super("Você não pode realizar um saque nesse momento!");
    }
}
