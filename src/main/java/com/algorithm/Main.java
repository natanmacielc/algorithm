package com.algorithm;

import com.algorithm.search.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List.of(
                new ConstantSearchAlgorithm(),
                new LinearSearchAlgorithm(),
                new LogarithmSearchAlgorithm(),
                new QuadraticSearchAlgorithm()
        ).forEach(new SearchAlgorithmUseCase()::execute);
    }
}