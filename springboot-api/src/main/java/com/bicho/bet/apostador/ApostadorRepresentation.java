package com.bicho.bet.apostador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Set;

public interface ApostadorRepresentation {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ApostadorCreate {
        @NotBlank
        @Size(min = 3, max = 20)
        private String username;

        @NotBlank
        @Size(max = 50)
        @Email
        private String email;

        private Set<String> role;

        @NotBlank
        @Size(min = 6, max = 40)
        private String password;

        @NotNull(message = "O nome não pode ser nulo")
        @NotEmpty(message = "O nome não pode ser vazio")
        private String nome;

        @NotNull(message = "O CPF não pode ser nulo")
        @NotEmpty(message = "O CPF não pode ser vazio")
        private String cpf;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ApostadorUpdate {
        @NotNull(message = "O nome não pode ser nulo")
        @NotEmpty(message = "O nome não pode ser vazio")
        private String nome;

        @NotNull(message = "O username não pode ser nulo")
        @NotEmpty(message = "O username não pode ser vazio")
        private String username;

        @NotNull(message = "O email não pode ser nulo")
        @NotEmpty(message = "O email não pode ser vazio")
        private String email;

        @NotNull(message = "A senha não pode ser nula")
        @NotEmpty(message = "A senha não pode ser vazia")
        private String password;

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
        private String username;
        private String email;
        private String cpf;
        private Double saldo;
        private Double depositado;
        private Double divida;
        private Double limite;

        public static ApostadorResponse from(Apostador apostador) {
            return ApostadorResponse.builder()
                    .id(apostador.getId())
                    .nome(apostador.getNome())
                    .username(apostador.getUsername())
                    .email(apostador.getEmail())
                    .cpf(apostador.getCpf())
                    .saldo(apostador.getSaldo())
                    .depositado(apostador.getDepositado())
                    .divida(apostador.getDivida())
                    .limite(apostador.getLimite())
                    .build();
        }
    }
}
