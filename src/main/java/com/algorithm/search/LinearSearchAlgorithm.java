package com.algorithm.search;

import static com.algorithm.array.TextArray.SPECIFIED_ELEMENT;

public class LinearSearchAlgorithm implements SearchAlgorithm {
    private long iterations = 0L;

    @Override
    public long iterations() {
        return iterations;
    }

    @Override
    public String execute(String[] array) {
        for (String text : array) {
            if (SPECIFIED_ELEMENT.equals(text)) {
                return text;
            }
            iterations++;
        }
        return null;
    }
}
