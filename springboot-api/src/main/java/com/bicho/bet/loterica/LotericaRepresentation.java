package com.bicho.bet.loterica;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

public interface LotericaRepresentation {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class LotericaCreate{
        @NotBlank
        @Size(min = 3, max = 20)
        private String username;

        @NotBlank
        @Size(max = 50)
        @Email
        private String email;

        @NotBlank
        @Size(min = 6, max = 40)
        private String password;

        @NotNull(message = "O nome não pode ser nulo")
        @NotEmpty(message = "O nome não pode ser vazio")
        private String nome;

        @NotNull(message = "O CNPJ não pode ser nulo")
        @NotEmpty(message = "O CNPJ não pode ser vazio")
        private String cnpj;

        @NotNull(message = "O telefone não pode ser nulo")
        @NotEmpty(message = "O telefone não pode ser vazio")
        private String telefone;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class LotericaUpdate{

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

        @NotNull(message = "O telefone não pode ser nulo")
        @NotEmpty(message = "O telefone não pode ser vazio")
        private String telefone;

        @NotNull(message = "O CNPJ não pode ser nulo")
        @NotEmpty(message = "O CNPJ não pode ser vazio")
        private String cnpj;

        @NotNull(message = "O saldo não pode ser nulo")
        @Positive(message = "O saldo deve ser maior que zero")
        private Double saldo;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class LotericaResponse{
        private Long id;
        private String nome;
        private String username;
        private String email;
        private String telefone;
        private String cnpj;
        private Double saldo;

        public static LotericaResponse from(Loterica loterica){
            return LotericaResponse.builder()
                    .id(loterica.getId())
                    .nome(loterica.getNome())
                    .username(loterica.getUsername())
                    .email(loterica.getEmail())
                    .telefone(loterica.getTelefone())
                    .cnpj(loterica.getCnpj())
                    .saldo(loterica.getSaldo())
                    .build();
        }
    }
}
