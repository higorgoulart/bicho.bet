package com.bicho.bet.bicho.bet.models.aposta;

import com.bicho.bet.bicho.bet.enums.Bicho;
import com.bicho.bet.bicho.bet.enums.TipoAposta;
import com.bicho.bet.bicho.bet.models.conta.Apostador;
import com.bicho.bet.bicho.bet.models.core.EntityId;
import com.bicho.bet.bicho.bet.models.jogo.Jogo;
import com.bicho.bet.bicho.bet.models.resultado.NumeroResultado;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Double obterMultiplicador(List<Integer> posicoes) {
        switch (tipo) {
            case GRUPO: 
            return posicoes.contains(1) ? 12.0 : 3.0;
            case DUQUE: 
            return posicoes.contains(1) && posicoes.contains(2)
                ? 95.0
                : posicoes.size() == 2
                    ? 12.0
                    : 1.0;
            case TERNO:
            return posicoes.contains(1) && posicoes.contains(2) && posicoes.contains(3)
                ? 700.0
                : posicoes.size() == 3
                    ? 42.0
                    : posicoes.size() == 2
                        ? 3.0
                        : 0.75;
            case QUADRA:
            return posicoes.contains(1) && posicoes.contains(2) && posicoes.contains(3) && posicoes.contains(4)
                ? 4000.0
                : posicoes.size() == 4
                    ? 500.0
                    : posicoes.size() == 3
                        ? 22.0
                        : posicoes.size() == 2
                            ? 1.5
                            : 0.2;
            case QUINA:
            return posicoes.contains(1) && posicoes.contains(2) && posicoes.contains(3) && posicoes.contains(4) && posicoes.contains(5)
                ? 17000.0
                : posicoes.size() == 4
                    ? 150.0
                    : posicoes.size() == 3
                        ? 8.0
                        : posicoes.size() == 2
                            ? 1.0
                            : 0.2;
            case DEZENA:
            return obterMultiplicadorNumerico(posicoes, 50.0, 7.0);
            case CENTENA:
            return obterMultiplicadorNumerico(posicoes, 500.0, 60.0);
            case MILHAR:
            return obterMultiplicadorNumerico(posicoes, 5000.0, 600.0);
        }

        return 0.0;
    }

    private Double obterMultiplicadorNumerico(List<Integer> posicoes, Double valorPrimeiro, Double valorOutro) {
        return posicoes.contains(1) 
            ? valorPrimeiro
            : posicoes.contains(2) || posicoes.contains(3) || posicoes.contains(4) || posicoes.contains(5) 
                ? valorOutro
                : 1.0;
    }
}
