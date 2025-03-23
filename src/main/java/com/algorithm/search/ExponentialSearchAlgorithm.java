package com.algorithm.search;

import java.util.Arrays;
import java.util.Objects;

import static com.algorithm.array.TextArray.SPECIFIED_ELEMENT;

public class ExponentialSearchAlgorithm implements SearchAlgorithm {
    private long iterations = 0;

    @Override
    public String execute(String[] array) {
        Arrays.sort(array);
        final int length = array.length;
        if (Objects.equals(array[0], SPECIFIED_ELEMENT)) {
            return array[0];
        }
        int i = 1;
        while (i < length && array[i].compareToIgnoreCase(SPECIFIED_ELEMENT) < 0) {
            iterations++;
            i *= 2;
        }
        final int index = binarySearch(array, i / 2, Math.min(i, length - 1));
        return index >= 0 ? array[index] : null;
    }

    private int binarySearch(String[] array, int fromIndex, int toIndex) {
        int low = fromIndex;
        int high = toIndex;
        while (low <= high) {
            iterations++;
            int mid = (low + high) >>> 1;
            final int compared = array[mid].compareToIgnoreCase(SPECIFIED_ELEMENT);
            if (compared < 0) {
                low = mid + 1;
            } else if (compared > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Override
    public long iterations() {
        return iterations;
    }
}
