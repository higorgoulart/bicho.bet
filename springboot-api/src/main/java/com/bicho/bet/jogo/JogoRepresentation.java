package com.bicho.bet.jogo;

import com.bicho.bet.loterica.Loterica;
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
        private LocalDateTime dataInicio;
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
