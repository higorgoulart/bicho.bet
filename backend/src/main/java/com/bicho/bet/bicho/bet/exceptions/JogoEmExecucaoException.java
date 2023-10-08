package com.bicho.bet.bicho.bet.exceptions;

public class JogoEmExecucaoException extends Exception {
  public JogoEmExecucaoException() {
    super("Não é possível abrir um novo jogo enquanto o jogo atual estiver em execução.");
  }
}
