package com.bicho.bet.animais;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BichoNumeros implements Serializable {
    private List<Integer> numero;

    public List<Integer> getNumero() {
        return numero;
    }
}
