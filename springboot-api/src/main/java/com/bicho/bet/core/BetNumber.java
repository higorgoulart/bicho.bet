package com.bicho.bet.core;

public class BetNumber extends Number implements Comparable<Number> {
    private final short value;

    public BetNumber(short value) {
        this.value = value;
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
        return Short.compare(this.value, (short) other);
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