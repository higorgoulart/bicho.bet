package com.bicho.bet.bicho.bet.models.resultado;

import com.bicho.bet.bicho.bet.models.core.EntityId;
import com.bicho.bet.bicho.bet.models.jogo.Jogo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Resultado extends EntityId {
    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @Column(name = "valor_acumulado")
    private Double valorAcumulado;

    @Column(name = "data")
    private LocalDateTime data;

    @OneToMany(mappedBy = "resultado")
    private List<NumeroResultado> numeros = new ArrayList<>();

    public Resultado(Jogo jogo) {
        this.jogo = jogo;
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
