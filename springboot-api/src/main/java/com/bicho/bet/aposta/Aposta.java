package com.bicho.bet.aposta;

import com.bicho.bet.apostador.Apostador;
import com.bicho.bet.core.BetNumber;
import com.bicho.bet.resultado.TipoResultado;
import com.bicho.bet.core.EntityId;
import com.bicho.bet.jogo.Jogo;
import com.bicho.bet.utils.ListUtils;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
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

    public Double obterPremio(List<BetNumber> resultados) {
        var posicoes = obterPosicoesCorretas(resultados);
        var multiplicador = obterMultiplicador(posicoes);

        return getValor() * multiplicador;
    }

    private List<TipoResultado> obterPosicoesCorretas(List<BetNumber> resultados) {
        var posicoes = new ArrayList<TipoResultado>();

        int i = 0;

        for (var resultado : resultados) {
            for (var apostado : getNumeros()) {
                boolean acerto;

                if (apostado.lessThan(100)) {
                    acerto = resultado.getDezena().equals(apostado);
                } else if (apostado.lessThan(1000)) {
                    acerto = resultado.getCentena().equals(apostado);
                } else {
                    acerto = resultado.equals(apostado);
                }

                if (acerto) {
                    posicoes.add(TipoResultado.valueOf(Integer.toString(i + 1)));
                } else if (getTipo() == TipoAposta.DEZENA ||
                           getTipo() == TipoAposta.CENTENA ||
                           getTipo() == TipoAposta.MILHAR) {
                    // TODO: SELECT NA TABELA DE BICHO - FAZER PELO PYTHON
//                    var bichoApostado = BichoType.valueOf(numeroAposta.toString());
//                    var bichoResultado = BichoType.valueOf(resultados.getDezena(i).toString());
//
//                    if (bichoApostado.equals(bichoResultado)) {
//                        posicoes.add(TipoResultado.BICHO);
//                    }
                }
            }

            i++;
        }

        return posicoes;
    }

    private Double obterMultiplicador(List<TipoResultado> posicoes) {
        if (posicoes.isEmpty()) {
            return 0.0;
        }

        return switch (tipo) {
            case GRUPO -> obterMultiplicadorGrupo(posicoes);
            case DUQUE -> obterMultiplicadorDuque(posicoes);
            case TERNO -> obterMultiplicadorTerno(posicoes);
            case QUADRA -> obterMultiplicadorQuadra(posicoes);
            case QUINA -> obterMultiplicadorQuina(posicoes);
            case DEZENA -> obterMultiplicadorDezena(posicoes);
            case CENTENA -> obterMultiplicadorCentena(posicoes);
            case MILHAR -> obterMultiplicadorMilhar(posicoes);
        };
    }

    private Double obterMultiplicadorGrupo(List<TipoResultado> posicoes) {
        return posicoes.contains(TipoResultado.PRIMEIRA) ? 12.0 : 3.0;
    }

    private Double obterMultiplicadorDuque(List<TipoResultado> posicoes) {
        return ListUtils.containsAll(posicoes, TipoResultado.PRIMEIRA, TipoResultado.SEGUNDA)
            ? 95.0
            : posicoes.size() == 2
                ? 12.0
                : 1.0;
    }

    private Double obterMultiplicadorTerno(List<TipoResultado> posicoes) {
        return ListUtils.containsAll(posicoes, TipoResultado.PRIMEIRA, TipoResultado.SEGUNDA, TipoResultado.TERCEIRA)
            ? 700.0
            : posicoes.size() == 3
                ? 42.0
                : posicoes.size() == 2
                    ? 3.0
                    : 0.75;
    }

    private Double obterMultiplicadorQuadra(List<TipoResultado> posicoes) {
        return ListUtils.containsAll(posicoes, TipoResultado.PRIMEIRA, TipoResultado.SEGUNDA, TipoResultado.TERCEIRA, TipoResultado.QUARTA)
            ? 4000.0
            : posicoes.size() == 4
                ? 500.0
                : posicoes.size() == 3
                    ? 22.0
                    : posicoes.size() == 2
                        ? 1.5
                        : 0.2;
    }

    private Double obterMultiplicadorQuina(List<TipoResultado> posicoes) {
        return ListUtils.containsAll(posicoes, TipoResultado.PRIMEIRA, TipoResultado.SEGUNDA, TipoResultado.TERCEIRA, TipoResultado.QUARTA, TipoResultado.QUINTA)
            ? 17000.0
            : posicoes.size() == 4
                ? 150.0
                : posicoes.size() == 3
                    ? 8.0
                    : posicoes.size() == 2
                        ? 1.0
                        : 0.2;
    }

    private Double obterMultiplicadorDezena(List<TipoResultado> posicoes) {
        return obterMultiplicadorNumerico(posicoes, 50.0, 7.0);
    }

    private Double obterMultiplicadorCentena(List<TipoResultado> posicoes) {
        return obterMultiplicadorNumerico(posicoes, 500.0, 60.0);
    }

    private Double obterMultiplicadorMilhar(List<TipoResultado> posicoes) {
        return obterMultiplicadorNumerico(posicoes, 5000.0, 600.0);
    }

    private Double obterMultiplicadorNumerico(List<TipoResultado> posicoes, Double valorPrimeiro, Double valorOutro) {
        return posicoes.contains(TipoResultado.PRIMEIRA)
            ? valorPrimeiro
            : ListUtils.containsAny(posicoes, TipoResultado.SEGUNDA, TipoResultado.TERCEIRA, TipoResultado.QUARTA, TipoResultado.QUINTA)
                ? valorOutro
                : 1.0;
    }
}
