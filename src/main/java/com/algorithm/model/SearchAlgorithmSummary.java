package com.algorithm.model;

public class SearchAlgorithmSummary {
    private SearchAlgorithmExecution diff;
    private SearchAlgorithmExecution iterations;

    public SearchAlgorithmSummary(SearchAlgorithmExecution diff, SearchAlgorithmExecution iterations) {
        this.diff = diff;
        this.iterations = iterations;
    }

    public void setDiff(SearchAlgorithmExecution diff) {
        this.diff = diff;
    }

    public void setIterations(SearchAlgorithmExecution iterations) {
        this.iterations = iterations;
    }

    public SearchAlgorithmExecution getDiff() {
        return diff;
    }

    public SearchAlgorithmExecution getIterations() {
        return iterations;
    }
}
