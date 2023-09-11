package com.bicho.bet.bicho.bet.models.aposta;

import com.bicho.bet.bicho.bet.enums.TipoAposta;
import com.bicho.bet.bicho.bet.models.conta.Apostador;
import com.bicho.bet.bicho.bet.models.core.EntityId;
import com.bicho.bet.bicho.bet.models.jogo.Jogo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aposta extends EntityId {
    @ManyToOne
    @JoinColumn(name = "apostador_id")
    private Apostador apostador;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data")
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoAposta tipo;

    @OneToMany(mappedBy = "aposta")
    private List<NumeroAposta> numeros = new ArrayList<>();

    public Aposta(Apostador apostador, Jogo jogo, Double valor, LocalDateTime data, TipoAposta tipo, List<NumeroAposta> numeros) {
        this.apostador = apostador;
        this.jogo = jogo;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.numeros = numeros;
    }

    public Apostador getApostador() {
        return apostador;
    }

    public Jogo getJogo() {
        return jogo;
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
