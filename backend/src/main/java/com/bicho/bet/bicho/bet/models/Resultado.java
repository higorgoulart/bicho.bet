package com.bicho.bet.bicho.bet.models;

import org.hibernate.mapping.Array;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Resultado extends EntityId{
    private Long idJogo;
    private Double valorAcumulado;
    private LocalDateTime data;
    private List<NumeroResultado> numeros = new ArrayList<>();

    public Long getIdJogo() {
        return idJogo;
    }

    public Double getValorAcumulado() {
        return valorAcumulado;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<NumeroResultado> getNumeroResultados() {
        return numeros;
    }
}
