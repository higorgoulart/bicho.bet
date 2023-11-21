package com.bicho.bet.aposta;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ApostaRepresentation {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ApostaCreate {
        @NotNull(message = "O apostador não pode ser nulo")
        private Long apostador;

        @NotNull(message = "O jogo não pode ser nulo")
        private Long jogo;

        @NotNull(message = "O valor não pode ser nulo")
        @Positive(message = "O valor deve ser maior que zero")
        private Double valor;

        @NotNull(message = "A data não pode ser nula")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime data;

        @NotNull(message = "O tipo de aposta não pode ser nulo")
        private TipoAposta tipo;

        @NotNull(message = "Os números não podem ser nulos")
        @NotEmpty(message = "Os números não podem ser vazios")
        private List<
                @Min(value = 0, message = "O valor deve ser maior ou igual a 0")
                @Max(value = 9999, message = "O valor deve ser menor ou igual a 9999")
                Short> numeros = new ArrayList<>();
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ApostaResponse {
        private Long id;
        private Long apostador;
        private Long jogo;
        private Double valor;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime dataHora;
        private TipoAposta tipo;
        private List<Short> numeros = new ArrayList<>();

        public static ApostaResponse from(Aposta aposta) {
            return ApostaResponse.builder()
                    .id(aposta.getId())
                    .apostador(aposta.getApostador().getId())
                    .jogo(aposta.getJogo().getId())
                    .valor(aposta.getValor())
                    .dataHora(aposta.getData())
                    .tipo(aposta.getTipo())
                    .numeros(aposta.getNumeros().stream().map(Number::shortValue).toList())
                    .build();
        }
    }
}
