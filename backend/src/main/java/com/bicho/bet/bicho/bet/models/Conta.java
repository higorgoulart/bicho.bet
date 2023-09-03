package com.bicho.bet.bicho.bet.models;

public abstract class Conta extends EntityId {
    private String nome;
    private String telefone;
    private double saldo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "nome='" + nome + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
