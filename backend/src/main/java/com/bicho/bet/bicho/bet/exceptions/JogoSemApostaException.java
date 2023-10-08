package com.bicho.bet.bicho.bet.exceptions;

public class JogoSemApostaException extends Exception {
    public JogoSemApostaException() {
        super("O jogo não contém nenhuma aposta!");
    }
}
