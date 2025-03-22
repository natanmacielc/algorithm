package com.algorithm.search;

import java.util.Objects;

import static com.algorithm.TextArray.SPECIFIED_ELEMENT;
import static java.lang.Thread.currentThread;

public class QuadraticSearchAlgorithm implements SearchAlgorithm {
    private long iterations = 0L;

    @Override
    public long iterations() {
        return iterations;
    }

    @Override
    public String search(String[] array) {
        for (String s : array) {
            for (int j = 0; j < array.length && !currentThread().isInterrupted(); j++) {
                if (Objects.equals(s, SPECIFIED_ELEMENT)) {
                    return s;
                }
                iterations++;
            }
            iterations++;
        }
        currentThread().interrupt();
        return null;
    }
}
