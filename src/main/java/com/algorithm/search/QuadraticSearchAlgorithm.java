package com.algorithm.search;

import java.util.Objects;

import static com.algorithm.TextArray.SPECIFIED_ELEMENT;

public class QuadraticSearchAlgorithm implements SearchAlgorithm {
    @Override
    public String search(String[] array) {
        long iterations = 0L;
        for (String s : array) {
            for (int j = 0; j < array.length; j++) {
                if (Objects.equals(s, SPECIFIED_ELEMENT)) {
                    printIterations(iterations);
                    return s;
                }
                iterations++;
            }
            iterations++;
        }
        return null;
    }
}
