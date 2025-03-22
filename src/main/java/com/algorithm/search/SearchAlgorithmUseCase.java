package com.algorithm.search;

import com.algorithm.Algorithm;
import com.algorithm.AlgorithmUseCase;
import com.algorithm.StopWatch;
import com.algorithm.model.SearchAlgorithmExecution;
import com.algorithm.model.SearchAlgorithmSummary;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import static com.algorithm.array.TextArray.SPECIFIED_ELEMENT;
import static com.algorithm.array.TextArray.textArray;
import static com.algorithm.constant.AnsiColor.*;
import static com.algorithm.constant.ArraySize.ARRAY_SIZES;
import static java.text.MessageFormat.format;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SearchAlgorithmUseCase implements AlgorithmUseCase {
    private final Map<Integer, SearchAlgorithmSummary> fastestAlgorithmExecution = new HashMap<>();
    private final Map<Integer, SearchAlgorithmSummary> slowestAlgorithmExecution = new HashMap<>();

    @Override
    public void execute(Algorithm<?, ?> algorithm) {
        final SearchAlgorithm searchAlgorithm = (SearchAlgorithm) algorithm;
        try (final ExecutorService executorService = newSingleThreadExecutor()) {
            ARRAY_SIZES.forEach(arraySize -> doSearch(arraySize, searchAlgorithm, executorService));
        }
    }

    @Override
    public void printPerformance() {
        ARRAY_SIZES.forEach(arraySize -> {
            final SearchAlgorithmSummary fasterAlgorithmSummary = fastestAlgorithmExecution.get(arraySize);
            final SearchAlgorithmSummary slowestAlgorithmSummary = slowestAlgorithmExecution.get(arraySize);
            System.out.println(format("{0}Faster execution of array with size {1}: {2} in {3}ms", ANSI_GREEN, arraySize, fasterAlgorithmSummary.getDiff().algorithm().getClass().getSimpleName(), fasterAlgorithmSummary.getDiff().execution()));
            System.out.println(format("Execution with fewer iterations of array with size {0}: {1} with {2} iterations{3}", arraySize, fasterAlgorithmSummary.getIterations().algorithm().getClass().getSimpleName(), fasterAlgorithmSummary.getIterations().execution(), ANSI_RESET));
            System.out.println(format("{0}Slower execution of array with size {1}: {2} in {3}ms", ANSI_RED, arraySize, slowestAlgorithmSummary.getDiff().algorithm().getClass().getSimpleName(), slowestAlgorithmSummary.getDiff().execution()));
            System.out.println(format("Execution with further iterations of array with size {0}: {1} with {2} iterations{3}", arraySize, slowestAlgorithmSummary.getIterations().algorithm().getClass().getSimpleName(), slowestAlgorithmSummary.getIterations().execution(), ANSI_RESET));
            System.out.println();
        });
    }

    private void doSearch(int arraySize, SearchAlgorithm searchAlgorithm, ExecutorService executorService) {
        final String[] array = textArray(arraySize);
        final StopWatch stopWatch = new StopWatch(searchAlgorithm.getClass().getSimpleName());
        final Future<String> task = executorService.submit(() -> searchAlgorithm.execute(array));
        stopWatch.start();
        searchInSingleThreadExecutor(arraySize, searchAlgorithm, task);
        stopWatch.stop();
        addExecutionTime(arraySize, searchAlgorithm, stopWatch.getDiff(), searchAlgorithm.iterations());
        System.out.println();
    }

    private void searchInSingleThreadExecutor(int arraySize, SearchAlgorithm searchAlgorithm, Future<String> task) {
        try {
            System.out.println(format("{0} searching for {1} in array with size {2}", searchAlgorithm.getClass().getSimpleName(), SPECIFIED_ELEMENT, arraySize));
            final String element = task.get(30, SECONDS);
            searchAlgorithm.printIterations(searchAlgorithm.iterations());
            System.out.println("Specified element " + element + " has been found");
        } catch (TimeoutException timeoutException) {
            System.out.println(ANSI_RED + "Search timed out"  + ANSI_RESET);
            searchAlgorithm.printIterations(searchAlgorithm.iterations());
            task.cancel(true);
        } catch (InterruptedException | ExecutionException executionException) {
            System.out.println(ANSI_RED + "Search interrupted" + ANSI_RESET);
        }
    }

    private void addExecutionTime(int arraySize, SearchAlgorithm algorithm, long diff, long iterations) {
        putFastestAlgorithmIfApplicable(arraySize, algorithm, diff, iterations);
        putSlowestAlgorithmIfApplicable(arraySize, algorithm, diff, iterations);
    }

    private void putSlowestAlgorithmIfApplicable(int arraySize, SearchAlgorithm algorithm, long diff, long iterations) {
        if (slowestAlgorithmExecution.containsKey(arraySize)) {
            if (slowestAlgorithmExecution.get(arraySize).getDiff().execution() < diff) {
                slowestAlgorithmExecution.get(arraySize).setDiff(new SearchAlgorithmExecution(algorithm, diff));
            }
            if (slowestAlgorithmExecution.get(arraySize).getIterations().execution() < iterations) {
                slowestAlgorithmExecution.get(arraySize).setIterations(new SearchAlgorithmExecution(algorithm, iterations));
            }
        } else {
            slowestAlgorithmExecution.put(arraySize,new SearchAlgorithmSummary(new SearchAlgorithmExecution(algorithm, diff), new SearchAlgorithmExecution(algorithm, iterations)));
        }
    }

    private void putFastestAlgorithmIfApplicable(int arraySize, SearchAlgorithm algorithm, long diff, long iterations) {
        final boolean notConstantAlgorithm = iterations != 0;
        if (fastestAlgorithmExecution.containsKey(arraySize)) {
            if (fastestAlgorithmExecution.get(arraySize).getDiff().execution() > diff && notConstantAlgorithm) {
                fastestAlgorithmExecution.get(arraySize).setDiff(new SearchAlgorithmExecution(algorithm, diff));
            }
            if (fastestAlgorithmExecution.get(arraySize).getIterations().execution() > iterations && notConstantAlgorithm) {
                fastestAlgorithmExecution.get(arraySize).setIterations(new SearchAlgorithmExecution(algorithm, iterations));
            }
        } else if (notConstantAlgorithm) {
            fastestAlgorithmExecution.put(arraySize, new SearchAlgorithmSummary(new SearchAlgorithmExecution(algorithm, diff), new SearchAlgorithmExecution(algorithm, iterations)));
        }
    }
}
