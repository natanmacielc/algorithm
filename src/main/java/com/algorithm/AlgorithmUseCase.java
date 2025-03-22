package com.algorithm;

public interface AlgorithmUseCase {
    void execute(Algorithm<?, ?> algorithm);
    void printPerformance();
}
