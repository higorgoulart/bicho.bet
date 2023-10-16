package com.bicho.bet.bicho.bet.exceptions;

public class NotFoundException extends RuntimeException {
    private static final String MESSAGE = " não encontrado.";

    public NotFoundException(String name) {
        super(name + MESSAGE);
    }

    public NotFoundException(String name, Throwable cause) {
        super(name + MESSAGE, cause);
    }
}
