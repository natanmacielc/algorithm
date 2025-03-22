package com.algorithm;

import com.algorithm.search.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<SearchAlgorithm> searchAlgorithms = List.of(
                new ConstantSearchAlgorithm(),
                new LinearSearchAlgorithm(),
                new LogarithmSearchAlgorithm(),
                new QuadraticSearchAlgorithm()
        );
        SearchAlgorithmUseCase searchAlgorithmUseCase = new SearchAlgorithmUseCase();
        searchAlgorithms.forEach(searchAlgorithmUseCase::execute);
        searchAlgorithmUseCase.printPerformance();
    }
}