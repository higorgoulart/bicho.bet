package com.bicho.bet.resultado;

import com.bicho.bet.core.EntityId;
import com.bicho.bet.jogo.Jogo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Resultado extends EntityId {
    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @Getter
    @Column(name = "data")
    private LocalDateTime data;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private NumeroResultado numeros;

    public Resultado(Jogo jogo) {
        this.jogo = jogo;
        this.numeros = gerarResultado();
    }

    public NumeroResultado getNumeroResultados() {
        return numeros;
    }

    public NumeroResultado gerarResultado() {
        var bichos = 12;
        var resultado = new NumeroResultado();
        var resultados = resultado.getNumeros();
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

            resultados.add(Short.parseShort(milhar));
        }

        return resultado;
    }
}
