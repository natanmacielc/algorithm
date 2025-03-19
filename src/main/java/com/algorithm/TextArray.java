package com.algorithm;

import java.util.Arrays;
import java.util.Random;

public class TextArray {
    public static String SPECIFIED_ELEMENT = "1234567890";
    public static int SPECIFIED_ELEMENT_INDEX = new Random().nextInt(999999);

    public static String[] textArray() {
        final String[] textArray = new String[1000000];
        Arrays.fill(textArray, "0");
        textArray[SPECIFIED_ELEMENT_INDEX] = SPECIFIED_ELEMENT;
        return textArray;
    }
}
