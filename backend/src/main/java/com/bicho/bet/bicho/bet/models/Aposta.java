package com.bicho.bet.bicho.bet.models;

import com.bicho.bet.bicho.bet.enums.TipoAposta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Aposta extends EntityId {
    private Long idApostador;
    private Long idJogo;
    private Double valor;
    private LocalDateTime data;
    private TipoAposta tipo;
    private List<NumeroAposta> numeros = new ArrayList<>();

    public Aposta(Long idApostador, Long idJogo, Double valor, LocalDateTime data, TipoAposta tipo, List<NumeroAposta> numeros) {
        this.idApostador = idApostador;
        this.idJogo = idJogo;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.numeros = numeros;
    }

    public Long getIdApostador() {
        return idApostador;
    }

    public Long getIdJogo() {
        return idJogo;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public TipoAposta getTipo() {
        return tipo;
    }

    public List<NumeroAposta> getNumeros() {
        return numeros;
    }
}
