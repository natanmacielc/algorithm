package com.algorithm.array;

import java.util.Arrays;
import java.util.Random;

public class TextArray {
    public static final String SPECIFIED_ELEMENT = "1234567890";
    public static int SPECIFIED_ELEMENT_INDEX = 0;

    public static String[] textArray(int arraySize) {
        final String[] textArray = new String[arraySize];
        SPECIFIED_ELEMENT_INDEX = new Random().nextInt(arraySize - 1);
        Arrays.fill(textArray, "0");
        textArray[SPECIFIED_ELEMENT_INDEX] = SPECIFIED_ELEMENT;
        return textArray;
    }
}
