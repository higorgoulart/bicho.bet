package com.bicho.bet.bicho.bet.utils;

import com.bicho.bet.bicho.bet.resultado.TipoResultado;

import java.util.Arrays;
import java.util.List;

public class ListUtils {
    public static boolean containsAll(List<TipoResultado> list, TipoResultado... elements) {
        return Arrays.stream(elements).allMatch(list::contains);
    }

    public static boolean containsAny(List<TipoResultado> list, TipoResultado... elements) {
        return Arrays.stream(elements).anyMatch(list::contains);
    }
}
