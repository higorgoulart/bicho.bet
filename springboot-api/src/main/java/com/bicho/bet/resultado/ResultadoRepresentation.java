package com.bicho.bet.resultado;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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

        private List<Short> numeros = new ArrayList<>();
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
        private List<Short> numeros = new ArrayList<>();

        public static ResultadoResponse from(Resultado resultado) {
            return ResultadoResponse.builder()
                    .id(resultado.getId())
                    .jogo(resultado.getJogo().getId())
                    .data(resultado.getData())
                    .numeros(resultado.getNumeros().getNumeros())
                    .build();
        }
    }
}
