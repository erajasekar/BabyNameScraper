package com.erajasekar.numerology;

/**
 * Created by relango on 4/6/15.
 */
public class NumerologicalValue {

    int value;
    int number;

    private NumerologicalValue(int value, int number) {
        this.value = value;
        this.number = number;
    }

    public static NumerologicalValue forValue(int value) {
        return new NumerologicalValue(value, NumerologyUtil.toNumeroNum(value));
    }

    public static NumerologicalValue forText(String text) {
        return forValue(NumerologyUtil.computeNumeroVal(text));
    }

    public int value() {
        return value;
    }

    public int number() {
        return number;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf(value)).append(" / ").append(number);
        return sb.toString();
    }
}
