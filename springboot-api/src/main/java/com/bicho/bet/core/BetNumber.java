package com.bicho.bet.core;

import java.util.Objects;

public class BetNumber extends Number implements Comparable<Number> {
    private final short value;

    public BetNumber(short value) {
        this.value = value;
    }

    public BetNumber(int value) {
        this.value = (short) value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public String toString() {
        return Short.toString(value);
    }

    @Override
    public int compareTo(Number other) {
        return Integer.compare(this.value, (int) other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() && o.getClass().getSuperclass() != Number.class) return false;

        return switch (o.getClass().getName()) {
            case "Integer" -> (int) value == (int) o;
            case "Short" -> value == (short) o;
            default -> value == ((BetNumber) o).value;
        };
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean greaterThan(Number other) {
        return compareTo(other) > 0;
    }

    public boolean lessThan(Number other) {
        return compareTo(other) < 0;
    }

    public BetNumber getDezena() {
        return new BetNumber(Short.parseShort(toString().substring(2)));
    }

    public BetNumber getCentena() {
        return new BetNumber(Short.parseShort(toString().substring(1)));
    }

    public static BetNumber parseBetNumber(Number value) {
        return new BetNumber((short) value);
    }

    public static BetNumber parseBetNumber(String value) {
        return parseBetNumber(Short.parseShort(value));
    }
}