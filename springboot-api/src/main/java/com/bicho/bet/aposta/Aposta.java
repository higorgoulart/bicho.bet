package com.bicho.bet.aposta;

import com.bicho.bet.apostador.Apostador;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.core.EntityId;
import com.bicho.bet.jogo.Jogo;
import com.bicho.bet.resultado.TipoResultado;
import com.bicho.bet.utils.ListUtils;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Aposta extends EntityId {
    @ManyToOne
    @JoinColumn(name = "apostador_id")
    private Apostador apostador;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "premio")
    private Double premio;

    @Column(name = "data")
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoAposta tipo;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<BetNumber> numeros = new ArrayList<>();

    public Aposta(Apostador apostador, Jogo jogo, Double valor, LocalDateTime data, TipoAposta tipo, List<BetNumber> numeros) {
        this.apostador = apostador;
        this.jogo = jogo;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.numeros = numeros;
    }
}
