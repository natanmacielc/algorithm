package com.algorithm;

public interface Algorithm<I, O> {
    O execute(I array);
    long iterations();
    default void printIterations(long iterations) {
        System.out.println(this.getClass().getSimpleName() + " iterations: " + iterations);
    }
}
