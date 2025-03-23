package com.algorithm;

import com.algorithm.search.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<SearchAlgorithm> searchAlgorithms = List.of(
                new ConstantSearchAlgorithm(),
                new LinearSearchAlgorithm(),
                new BinarySearchAlgorithm(),
                new TernarySearchAlgorithm(),
                new JumpSearchAlgorithm(),
                new ExponentialSearchAlgorithm(),
                new InterpolationSearchAlgorithm(),
                new QuadraticSearchAlgorithm()
        );
        final SearchAlgorithmUseCase searchAlgorithmUseCase = new SearchAlgorithmUseCase();
        searchAlgorithms.forEach(searchAlgorithmUseCase::execute);
        searchAlgorithmUseCase.printPerformance();
    }
}