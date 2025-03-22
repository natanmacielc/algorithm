package com.algorithm.search;

import com.algorithm.Algorithm;

public interface SearchAlgorithm extends Algorithm {
    long iterations();
    String search(String[] array);
}
