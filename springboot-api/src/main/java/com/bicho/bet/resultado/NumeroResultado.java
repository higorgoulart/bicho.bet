package com.bicho.bet.resultado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NumeroResultado implements Serializable {
    private List<Short> numeros;

    public Short getDezena(Integer index) {
        return Short.parseShort(numeros.get(index).toString().substring(2));
    }

    public Short getCentena(Integer index) {
        return Short.parseShort(numeros.get(index).toString().substring(1));
    }
}
