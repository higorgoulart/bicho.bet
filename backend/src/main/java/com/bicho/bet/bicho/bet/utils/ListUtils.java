package com.bicho.bet.bicho.bet.utils;

import java.util.List;

public class ListUtils {
    public static boolean containsAll(List<Integer> list, Integer... elements) {
        for (Integer element : elements) {
            if (!list.contains(element)) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAny(List<Integer> list, Integer... elements) {
        for (Integer element : elements) {
            if (list.contains(element)) {
                return true;
            }
        }

        return false;
    }
}
