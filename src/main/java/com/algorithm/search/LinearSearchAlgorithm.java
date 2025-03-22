package com.algorithm.search;

import static com.algorithm.TextArray.SPECIFIED_ELEMENT;

public class LinearSearchAlgorithm implements SearchAlgorithm {
    private long iterations = 0L;

    @Override
    public long iterations() {
        return iterations;
    }

    @Override
    public String search(String[] array) {
        for (String text : array) {
            if (SPECIFIED_ELEMENT.equals(text)) {
                return text;
            }
            iterations++;
        }
        return null;
    }
}
