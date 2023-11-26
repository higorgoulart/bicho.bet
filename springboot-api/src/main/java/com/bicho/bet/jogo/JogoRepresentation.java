package com.bicho.bet.jogo;

import com.bicho.bet.loterica.Loterica;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public interface JogoRepresentation {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class JogoResponse {
        private Long id;
        private Loterica loterica;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime dataInicio;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime dataFim;
        @Enumerated(EnumType.STRING)
        private StatusJogo status;


        public static JogoResponse from(Jogo jogo) {
            return JogoResponse.builder()
                    .id(jogo.getId())
                    .loterica(jogo.getLoterica())
                    .dataInicio(jogo.getDataInicio())
                    .dataFim(jogo.getDataFim())
                    .status(jogo.getStatus()).build();
        }
    }

}
