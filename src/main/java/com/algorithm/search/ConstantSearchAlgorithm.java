package com.algorithm.search;

import static com.algorithm.array.TextArray.SPECIFIED_ELEMENT_INDEX;

public class ConstantSearchAlgorithm implements SearchAlgorithm {
    @Override
    public long iterations() {
        return 0;
    }

    @Override
    public String execute(String[] array) {
        return array[SPECIFIED_ELEMENT_INDEX];
    }
}
