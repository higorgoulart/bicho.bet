package com.bicho.bet.resultado;

import com.bicho.bet.core.BetNumber;
import com.bicho.bet.core.EntityId;
import com.bicho.bet.jogo.Jogo;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Resultado extends EntityId {
    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @Getter
    @Column(name = "data")
    private LocalDateTime data;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<BetNumber> numeros = new ArrayList<>();

    public List<BetNumber> gerarResultado() {
        var bichos = 12;
        var resultados = new ArrayList<BetNumber>();
        var random = new Random();

        for (var i = 1; i <= 5; i++) {
            var numeroBicho = Integer.toString(random.nextInt(100));
//            BichoType bichoSorteado;
//
//            do {
//                bichoSorteado = BichoType.valueOf(numeroBicho);
//            } while (bichos.contains(bichoSorteado));

//            bichos.add(bichoSorteado);

            var milhar = random.nextInt(100) + numeroBicho;

            resultados.add(BetNumber.parseBetNumber(milhar));
        }

        return resultados;
    }
}
