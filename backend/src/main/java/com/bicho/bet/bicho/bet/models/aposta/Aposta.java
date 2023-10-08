package com.bicho.bet.bicho.bet.models.aposta;

import com.bicho.bet.bicho.bet.enums.Bicho;
import com.bicho.bet.bicho.bet.enums.TipoAposta;
import com.bicho.bet.bicho.bet.enums.TipoResultado;
import com.bicho.bet.bicho.bet.models.conta.Apostador;
import com.bicho.bet.bicho.bet.models.core.EntityId;
import com.bicho.bet.bicho.bet.models.jogo.Jogo;
import com.bicho.bet.bicho.bet.models.resultado.NumeroResultado;
import com.bicho.bet.bicho.bet.utils.ListUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
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

    public Double obterPremio(List<NumeroResultado> resultados) {
        var posicoes = obterPosicoesCorretas(resultados);
        var multiplicador = obterMultiplicador(posicoes);

        return getValor() * multiplicador;
    }

    private List<TipoResultado> obterPosicoesCorretas(List<NumeroResultado> resultados) {
        var posicoes = new ArrayList<TipoResultado>();

        int i = 1;

        for (var resultado : resultados) {
            for (var apostado : getNumeros()) {
                var numeroAposta = apostado.getNumero();
                boolean acerto;

                if (numeroAposta < 100) {
                    acerto = resultado.getDezena().equals(numeroAposta);
                } else if (numeroAposta < 1000) {
                    acerto = resultado.getCentena().equals(numeroAposta);
                } else {
                    acerto = resultado.getNumero().equals(numeroAposta);
                }

                if (acerto) {
                    posicoes.add(TipoResultado.valueOf(Integer.toString(i)));
                } else if (getTipo() == TipoAposta.DEZENA ||
                           getTipo() == TipoAposta.CENTENA ||
                           getTipo() == TipoAposta.MILHAR) {
                    var bichoApostado = Bicho.valueOf(numeroAposta.toString());
                    var bichoResultado = Bicho.valueOf(resultado.getDezena().toString());

                    if (bichoApostado.equals(bichoResultado)) {
                        posicoes.add(TipoResultado.BICHO);
                    }
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
