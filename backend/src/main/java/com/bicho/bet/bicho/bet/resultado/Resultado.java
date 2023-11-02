package com.bicho.bet.bicho.bet.resultado;

import com.bicho.bet.bicho.bet.aposta.BichoType;
import com.bicho.bet.bicho.bet.core.EntityId;
import com.bicho.bet.bicho.bet.jogo.Jogo;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@NoArgsConstructor
public class Resultado extends EntityId {
    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @Column(name = "data")
    private LocalDateTime data;

    @OneToMany(mappedBy = "resultado")
    private List<NumeroResultado> numeros = new ArrayList<>();

    public Resultado(Jogo jogo) {
        this.jogo = jogo;
        this.numeros = gerarResultado();
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<NumeroResultado> getNumeroResultados() {
        return numeros;
    }

    public List<NumeroResultado> gerarResultado() {
        var bichos = new ArrayList<BichoType>();
        var resultados = new ArrayList<NumeroResultado>();
        var random = new Random();

        for (var i = 1; i <= 5; i++) {
            var numeroBicho = Integer.toString(random.nextInt(100));
            BichoType bichoSorteado;

            do {
                bichoSorteado = BichoType.valueOf(numeroBicho);
            } while (bichos.contains(bichoSorteado));

            bichos.add(bichoSorteado);

            var milhar = random.nextInt(100) + numeroBicho;

            resultados.add(new NumeroResultado(this, Short.parseShort(milhar)));
        }

        return resultados;
    }
}
