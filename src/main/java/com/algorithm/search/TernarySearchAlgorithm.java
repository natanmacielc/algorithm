package com.algorithm.search;

import java.util.Arrays;
import java.util.Objects;

import static com.algorithm.array.TextArray.SPECIFIED_ELEMENT;

public class TernarySearchAlgorithm implements SearchAlgorithm {
    private long iterations = 0L;

    @Override
    public long iterations() {
        return iterations;
    }

    @Override
    public String execute(String[] array) {
        Arrays.sort(array);
        return ternarySearch(array, 0, array.length - 1);
    }

    private String ternarySearch(String[] array, int left, int right) {
        iterations++;
        if (right >= left) {
            int leftMid = left + (right - left) / 3;
            int rightMid = right - (right - left) / 3;
            if (Objects.equals(array[leftMid], SPECIFIED_ELEMENT)) {
                return array[leftMid];
            }
            if (Objects.equals(array[rightMid], SPECIFIED_ELEMENT)) {
                return array[rightMid];
            }
            if (SPECIFIED_ELEMENT.compareToIgnoreCase(array[leftMid]) < 0) {
                return ternarySearch(array, left, leftMid - 1);
            }
            else if (SPECIFIED_ELEMENT.compareToIgnoreCase(array[rightMid]) > 0) {
                return ternarySearch(array, rightMid + 1, right);
            } else {
                return ternarySearch(array, leftMid + 1, rightMid - 1);
            }
        }
        return null;
    }
}
