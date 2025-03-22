package com.algorithm.search;

import java.util.Arrays;

import static com.algorithm.TextArray.SPECIFIED_ELEMENT;

public class LogarithmSearchAlgorithm implements SearchAlgorithm {
    private long iterations = 0L;

    @Override
    public long iterations() {
        return iterations;
    }

    @Override
    public String search(String[] array) {
        Arrays.sort(array);
        int left = 0, right = array.length - 1;
        while (left <= right) {
            final int mid = left + (right - left) / 2;
            if (SPECIFIED_ELEMENT.equals(array[mid])) {
                return array[mid];
            } else if (SPECIFIED_ELEMENT.compareToIgnoreCase(array[mid]) > 0) {
                left = mid + 1;
            } else {
                left = mid - 1;
            }
            iterations++;
        }
        return null;
    }
}
