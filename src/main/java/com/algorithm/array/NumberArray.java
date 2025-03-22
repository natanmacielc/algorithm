package com.algorithm.array;

import java.util.Random;

public class NumberArray {
    public static int[] numberArray(int arraySize) {
        final int[] numberArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            numberArray[i] = new Random().nextInt(arraySize - 1);
        }
        return numberArray;
    }
}
