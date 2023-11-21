package com.bicho.bet.aposta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class NumeroAposta implements Serializable {
    private List<Short> numeros;
}
