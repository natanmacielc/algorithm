package com.algorithm.search;

import static com.algorithm.TextArray.SPECIFIED_ELEMENT;

public class LinearSearchAlgorithm implements SearchAlgorithm {
    @Override
    public String search(String[] array) {
        long iterations = 0;
        for (String text : array) {
            if (SPECIFIED_ELEMENT.equals(text)) {
                printIterations(iterations);
                return text;
            }
            iterations++;
        }
        return null;
    }
}
