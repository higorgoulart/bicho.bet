package com.bicho.bet.bicho.bet.enums;

public enum Bicho {
    AVESTRUZ(1, 2, 3, 4),
    AGUIA(5, 6, 7, 8),
    BURRO(9, 10, 11, 12),
    BORBOLETA(13, 14, 15, 16),
    CACHORRO(17, 18, 19, 20),
    CABRA(21, 22, 23, 24),
    CARNEIRO(25, 26, 27, 28),
    CAMELO(29, 30, 31, 32),
    COBRA(33, 34, 35, 36),
    COELHO(37, 38, 39, 40),
    CAVALO(41, 42, 43, 44),
    ELEFANTE(45, 46, 47, 48),
    GALO(49, 50, 51, 52),
    GATO(53, 54, 55, 56),
    JACARE(57, 58, 59, 60),
    LEAO(61, 62, 63, 64),
    MACACO(65, 66, 67, 68),
    PORCO(69, 70, 71, 72),
    PAVAO(73, 74, 75, 76),
    PERU(77, 78, 79, 80),
    TOURO(81, 82, 83, 84),
    TIGRE(85, 86, 87, 88),
    URSO(89, 90, 91, 92),
    VEADO(93, 94, 95, 96),
    VACA(97, 98, 99, 0);

    private final Object[] values;

    Bicho(Object... vals) {
        values = vals;
    }
}