package com.algorithm;

public class StopWatch {
    private final String algorithm;
    private long startTime;

    public StopWatch(String algorithm) {
        this.algorithm = algorithm;
    }

    public void start() {
        System.out.println("Starting ".concat(algorithm).concat(" algorithm execution..."));
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        long stopTime = System.currentTimeMillis();
        long diff = stopTime - startTime;
        System.out.println(algorithm.concat(" execution time: ".concat(String.valueOf(diff))));
    }
}
