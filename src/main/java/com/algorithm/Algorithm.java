package com.algorithm;

public interface Algorithm {
    default void printIterations(long iterations) {
        System.out.println(this.getClass().getSimpleName() + " iterations: " + iterations);
    }
}
