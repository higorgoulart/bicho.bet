package com.bicho.bet.historico;

import com.bicho.bet.aposta.TipoAposta;
import com.bicho.bet.core.BetNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HistoricoResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data;
    private String loterica;
    private TipoAposta tipoAposta;
    private List<BetNumber> numerosAposta;
    private List<BetNumber> numerosResultado;
    private double aposta;
    private double ganhos;

    public HistoricoResponse(
            LocalDateTime data,
            String loterica,
            TipoAposta tipoAposta,
            Object numerosAposta,
            Object numerosResultado,
            Double valor
    ) {
        this.data = data;
        this.loterica = loterica;
        this.tipoAposta = tipoAposta;
        this.numerosAposta = (List<BetNumber>) numerosAposta;
        this.numerosResultado = (List<BetNumber>) numerosResultado;
        this.aposta = valor;
        this.ganhos = 0.0 - valor;
    }
}
