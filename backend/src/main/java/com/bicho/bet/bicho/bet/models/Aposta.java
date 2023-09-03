package com.bicho.bet.bicho.bet.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aposta extends EntityId {
    private Long idApostador;
    private Long idJogo;
    private Double valor;
    private LocalDate data;
    private TipoAposta tipo;
    private List<NumeroAposta> numeros = new ArrayList<>();

    public Long getIdApostador() {
        return idApostador;
    }

    public Long getIdJogo() {
        return idJogo;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoAposta getTipo() {
        return tipo;
    }

    public List<NumeroAposta> getNumeros() {
        return numeros;
    }
}
