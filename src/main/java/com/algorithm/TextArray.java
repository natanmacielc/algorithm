package com.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TextArray {
    private static final int TINY_ARRAY_SIZE = 1000;
    private static final int AVERAGE_ARRAY_SIZE = 100000;
    private static final int LARGE_ARRAY_SIZE = 10000000;
    public static final String SPECIFIED_ELEMENT = "1234567890";
    public static final List<Integer> ARRAY_SIZES = Arrays.asList(TINY_ARRAY_SIZE, AVERAGE_ARRAY_SIZE, LARGE_ARRAY_SIZE);
    public static int SPECIFIED_ELEMENT_INDEX = 0;

    public static String[] textArray(int arraySize) {
        final String[] textArray = new String[arraySize];
        SPECIFIED_ELEMENT_INDEX = new Random().nextInt(arraySize - 1);
        Arrays.fill(textArray, "0");
        textArray[SPECIFIED_ELEMENT_INDEX] = SPECIFIED_ELEMENT;
        return textArray;
    }
}
