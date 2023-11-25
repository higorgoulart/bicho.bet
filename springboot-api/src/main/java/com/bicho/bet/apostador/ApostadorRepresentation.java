package com.bicho.bet.apostador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public interface ApostadorRepresentation {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ApostadorCreateUpdate {
        @NotNull(message = "O nome não pode ser nulo")
        @NotEmpty(message = "O nome não pode ser vazio")
        private String nome;

        @NotNull(message = "O telefone não pode ser nulo")
        @NotEmpty(message = "O telefone não pode ser vazio")
        private String telefone;

        @NotNull(message = "O CPF não pode ser nulo")
        @NotEmpty(message = "O CPF não pode ser vazio")
        private String cpf;

        @NotNull(message = "O saldo não pode ser nulo")
        @Positive(message = "O saldo deve ser maior que zero")
        private Double saldo;

        @NotNull(message = "O depositado não pode ser nulo")
        @Positive(message = "O depositado deve ser maior que zero")
        private Double depositado;

        @NotNull(message = "A dívida não pode ser nula")
        @Positive(message = "A dívida deve ser maior que zero")
        private Double divida;

        @NotNull(message = "O limite não pode ser nulo")
        @Positive(message = "O limite deve ser maior que zero")
        private Double limite;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ApostadorResponse {
        private Long id;
        private String nome;
        private String telefone;
        private String cpf;
        private Double saldo;
        private Double depositado;
        private Double divida;
        private Double limite;

        public static ApostadorResponse from(Apostador apostador) {
            return ApostadorResponse.builder()
                    .id(apostador.getId())
                    .nome(apostador.getNome())
                    .telefone(apostador.getTelefone())
                    .cpf(apostador.getCpf())
                    .saldo(apostador.getSaldo())
                    .depositado(apostador.getDepositado())
                    .divida(apostador.getDivida())
                    .limite(apostador.getLimite())
                    .build();
        }
    }
}
