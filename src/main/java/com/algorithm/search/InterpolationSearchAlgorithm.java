package com.algorithm.search;

import java.util.Arrays;

import static com.algorithm.array.TextArray.SPECIFIED_ELEMENT;
import static java.lang.Integer.parseInt;

public class InterpolationSearchAlgorithm implements SearchAlgorithm {
    private long iterations = 0;

    @Override
    public String execute(String[] array) {
        Arrays.sort(array);
        final int target = parseInt(SPECIFIED_ELEMENT);
        int lowest = 0, highest = array.length - 1;
        while (lowest <= highest && target >= parseInt(array[lowest]) && target <= parseInt(array[highest])) {
            iterations++;
            if (parseInt(array[lowest]) == parseInt(array[highest])) {
                if (parseInt(array[lowest]) == target) {
                    return array[lowest];
                } else {
                    return null;
                }
            }
            int position = lowest + ((target - parseInt(array[lowest])) * (highest - lowest)) / (parseInt(array[highest]) - parseInt(array[lowest]));
            position = Math.max(lowest, Math.min(highest, position));
            if (parseInt(array[position]) == target) {
                return array[position];
            } else if (parseInt(array[position]) < target) {
                lowest = position + 1;
            } else {
                highest = position - 1;
            }
        }
        return null;
    }

    @Override
    public long iterations() {
        return iterations;
    }
}