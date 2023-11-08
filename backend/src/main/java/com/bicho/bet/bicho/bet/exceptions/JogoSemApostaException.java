package com.bicho.bet.bicho.bet.exceptions;

public class JogoSemApostaException extends RuntimeException {
    public JogoSemApostaException() {
        super("O jogo não contém nenhuma aposta!");
    }
}
