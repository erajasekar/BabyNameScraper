package com.erajasekar.numerology;

/**
 * Created by relango on 4/6/15.
 */
public class NumerologyUtil {

    public static int computeNumeroVal(String text){

        char []chars = text.toCharArray();
        int val = 0;

        for(char c : chars){

            if (Character.isLetter(c)){
                val = val + Alphabet.numeroValOf(c);
            }else if (Character.isDigit(c)){
                val = val + Integer.valueOf(Character.toString(c));
            }
        }

        return val;
    }

    public static int toNumeroNum(int numeroVal){

        int num = numeroVal % 9;

        if (num == 0){
            num = 9;
        }

        return num;
    }
}
