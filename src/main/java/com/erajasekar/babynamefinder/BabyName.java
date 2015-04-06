package com.erajasekar.babynamefinder;

import com.erajasekar.numerology.NumerologicalValue;

/**
 * Created by relango on 4/6/15.
 */
public class BabyName {

    private String name;
    private Gender gender;
    private NumerologicalValue numerologicalValue;

    public BabyName(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.numerologicalValue = NumerologicalValue.forText(name);
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public NumerologicalValue getNumerologicalValue() {
        return numerologicalValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(name).append(" => ").append(numerologicalValue);
        return sb.toString();
    }
}
