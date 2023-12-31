package com.bicho.bet.jogo;

import com.bicho.bet.loterica.Loterica;
import com.bicho.bet.core.EntityId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@SequenceGenerator(name = EntityId.SEQUENCE_GENERATOR, sequenceName = "tb_jogo_sequence")
public class Jogo extends EntityId {
    @ManyToOne
    @JoinColumn(name = "loterica_id")
    private Loterica loterica;

    @Column(name = "dt_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "dt_fim")
    private LocalDateTime dataFim;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusJogo status;

    public Jogo(Loterica loterica, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.loterica = loterica;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
