package com.bicho.bet.bicho.bet.models;

import java.time.LocalDateTime;

public class Jogo extends EntityId {
    private Long idLoterica;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public Jogo(Long idLoterica, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.idLoterica = idLoterica;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    public Long getIdLoterica() {
        return idLoterica;
    }

    public void setIdLoterica(Long idLoterica) {
        this.idLoterica = idLoterica;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
}
