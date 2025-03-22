package com.algorithm.search;

import com.algorithm.Algorithm;
import com.algorithm.AlgorithmUseCase;
import com.algorithm.StopWatch;
import com.algorithm.model.SearchAlgorithmExecution;
import com.algorithm.model.SearchAlgorithmSummary;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static com.algorithm.TextArray.*;
import static com.algorithm.constant.AnsiColor.ANSI_GREEN;
import static com.algorithm.constant.AnsiColor.ANSI_RESET;
import static java.text.MessageFormat.format;
import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class SearchAlgorithmUseCase implements AlgorithmUseCase {
    private final Map<Integer, SearchAlgorithmSummary> algorithmExecutionTimes = new HashMap<>();

    @Override
    public void execute(Algorithm algorithm) {
        final SearchAlgorithm searchAlgorithm = (SearchAlgorithm) algorithm;
        try (final ExecutorService executorService = newSingleThreadExecutor()) {
            ARRAY_SIZES.forEach(arraySize -> doSearch(arraySize, searchAlgorithm, executorService));
        }
    }

    @Override
    public void printPerformance() {
        algorithmExecutionTimes.forEach((arraySize, searchAlgorithmExecutionTime) ->    {
            final SearchAlgorithmExecution diff = searchAlgorithmExecutionTime.getDiff();
            final SearchAlgorithmExecution iterations = searchAlgorithmExecutionTime.getIterations();
            System.out.println(format("{0}Faster execution of array with size {1}: {2} with {3}ms", ANSI_GREEN, arraySize, diff.algorithm().getClass().getSimpleName(), diff.execution()));
            System.out.println(format("Execution with fewer iterations of array with size  {0}: {1} with {2} iterations{3}", arraySize, iterations.algorithm().getClass().getSimpleName(), iterations.execution(), ANSI_RESET));
            System.out.println();
        });
    }

    private void doSearch(int arraySize, SearchAlgorithm searchAlgorithm, ExecutorService executorService) {
        final String[] array = textArray(arraySize);
        final StopWatch stopWatch = new StopWatch(searchAlgorithm.getClass().getSimpleName());
        final Future<String> task = executorService.submit(() -> searchAlgorithm.search(array));
        stopWatch.start();
        try {
            System.out.println(format("{0} searching for {1} in array size {2}", searchAlgorithm.getClass().getSimpleName(), SPECIFIED_ELEMENT, arraySize));
            final String element = task.get(30, TimeUnit.SECONDS);
            searchAlgorithm.printIterations(searchAlgorithm.iterations());
            System.out.println("Specified element " + element + " has been found");
        } catch (TimeoutException timeoutException) {
            System.out.println("Search timed out");
            searchAlgorithm.printIterations(searchAlgorithm.iterations());
            task.cancel(true);
        } catch (InterruptedException | ExecutionException executionException) {
            System.out.println("Search interrupted");
        }
        stopWatch.stop();
        addExecutionTime(arraySize, searchAlgorithm, stopWatch.getDiff(), searchAlgorithm.iterations());
        System.out.println();
    }

    private void addExecutionTime(int arraySize, SearchAlgorithm algorithm, long diff, long iterations) {
        final boolean notConstantAlgorithm = iterations != 0;
        if (algorithmExecutionTimes.containsKey(arraySize)) {
            if (algorithmExecutionTimes.get(arraySize).getDiff().execution() > diff && notConstantAlgorithm) {
                algorithmExecutionTimes.get(arraySize).setDiff(new SearchAlgorithmExecution(algorithm, diff));
            } else if (algorithmExecutionTimes.get(arraySize).getIterations().execution() > iterations && notConstantAlgorithm) {
                algorithmExecutionTimes.get(arraySize).setIterations(new SearchAlgorithmExecution(algorithm, iterations));
            }
        } else if (notConstantAlgorithm) {
            algorithmExecutionTimes.put(arraySize, new SearchAlgorithmSummary(new SearchAlgorithmExecution(algorithm, diff), new SearchAlgorithmExecution(algorithm, iterations)));
        }
    }
}
