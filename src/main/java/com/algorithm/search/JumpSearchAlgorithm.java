package com.algorithm.search;

import java.util.Arrays;
import java.util.Objects;

import static com.algorithm.array.TextArray.SPECIFIED_ELEMENT;

public class JumpSearchAlgorithm implements SearchAlgorithm {
    private long iterations = 0;

    @Override
    public String execute(String[] array) {
        Arrays.sort(array);
        final int size = array.length;
        int step = (int) Math.floor(Math.sqrt(size));
        int previous = 0;
        for (
                int minStep = Math.min(step, size) - 1;
                array[minStep].compareToIgnoreCase(SPECIFIED_ELEMENT) < 0;
                minStep = Math.min(step, size) - 1
        ) {
            iterations++;
            previous = step;
            step += (int) Math.floor(Math.sqrt(size));
            if (previous >= size) {
                return null;
            }
        }
        while (array[previous].compareToIgnoreCase(SPECIFIED_ELEMENT) < 0) {
            iterations++;
            previous++;
            if (previous == Math.min(step, size)) {
                return null;
            }
        }
        if (Objects.equals(array[previous], SPECIFIED_ELEMENT)) {
            return array[previous];
        }
        return null;
    }

    @Override
    public long iterations() {
        return iterations;
    }
}
