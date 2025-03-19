package com.algorithm.search;

import com.algorithm.StopWatch;

import static com.algorithm.TextArray.*;

public class ConstantSearchAlgorithm implements SearchAlgorithm {
    @Override
    public String search(String[] array) {
        final String text = array[SPECIFIED_ELEMENT_INDEX];
        printIterations(0);
        return text;
    }
}
