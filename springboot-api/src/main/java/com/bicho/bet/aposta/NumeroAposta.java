package com.bicho.bet.aposta;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NumeroAposta implements Serializable {
    private List<Short> numeros;
}
