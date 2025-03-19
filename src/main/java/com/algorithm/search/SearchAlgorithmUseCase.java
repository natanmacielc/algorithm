package com.algorithm.search;

import com.algorithm.Algorithm;
import com.algorithm.AlgorithmUseCase;
import com.algorithm.StopWatch;

import static com.algorithm.TextArray.textArray;

public class SearchAlgorithmUseCase implements AlgorithmUseCase {
    @Override
    public void execute(Algorithm algorithm) {
        final SearchAlgorithm searchAlgorithm = (SearchAlgorithm) algorithm;
        final String[] array = textArray();
        final StopWatch stopWatch = new StopWatch(algorithm.getClass().getSimpleName());
        stopWatch.start();
        final String element = searchAlgorithm.search(array);
        stopWatch.stop();
        System.out.println("Specified element " + element + " has been found");
        System.out.println();
    }
}
