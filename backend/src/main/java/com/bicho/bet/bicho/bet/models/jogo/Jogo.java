package com.bicho.bet.bicho.bet.models.jogo;

import com.bicho.bet.bicho.bet.models.conta.Loterica;
import com.bicho.bet.bicho.bet.models.core.EntityId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Jogo extends EntityId {
    @ManyToOne
    @JoinColumn(name = "loterica_id")
    private Loterica loterica;

    @Column(name = "dt_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "dt_fim")
    private LocalDateTime dataFim;

    public Jogo(Loterica loterica, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.loterica = loterica;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Jogo() {

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
