package com.bicho.bet.resultado;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NumeroResultado implements Serializable {
    private List<Short> numeros = new ArrayList<>();

    public Short getDezena(Integer index) {
        return Short.parseShort(numeros.get(index).toString().substring(2));
    }

    public Short getCentena(Integer index) {
        return Short.parseShort(numeros.get(index).toString().substring(1));
    }
}
