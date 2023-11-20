package com.bicho.bet.resultado;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ResultadoRepresentation {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ResultadoCreate {
        @NotNull(message = "O jogo não pode ser nulo")
        private Long jogo;

        @NotNull(message = "A data não pode ser nula")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime data;

        private List<NumeroResultadoCreate> numeros = new ArrayList<>();
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class NumeroResultadoCreate {
        @NotNull(message = "O número não pode ser nulo")
        @Positive(message = "O número deve ser maior que zero")
        private Short numero;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ResultadoResponse {
        private Long id;
        private Long jogo;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime data;
        private List<NumeroResultadoResponse> numeros = new ArrayList<>();

        public static ResultadoResponse from(Resultado aposta) {
            return ResultadoResponse.builder()
                    .id(aposta.getId())
                    .jogo(aposta.getJogo().getId())
                    .data(aposta.getData())
                    .numeros(aposta.getNumeros().getNumeros().stream().map(NumeroResultadoResponse::from).toList())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class NumeroResultadoResponse {
        private Short numero;

        public static NumeroResultadoResponse from(Short numero) {
            return NumeroResultadoResponse.builder()
                    .numero(numero)
                    .build();
        }
    }
}
