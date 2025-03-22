package com.algorithm;

import static com.algorithm.constant.AnsiColor.*;
import static java.text.MessageFormat.format;

public class StopWatch {
    private final String algorithm;
    private long startTime;
    private long diff;

    public StopWatch(String algorithm) {
        this.algorithm = algorithm;
    }

    public void start() {
        System.out.println(format("{0}Starting {1} algorithm execution...{2}", ANSI_GREEN, algorithm, ANSI_RESET));
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        final long stopTime = System.currentTimeMillis();
        diff = stopTime - startTime;
        System.out.println(format("{0}{1} execution time: {2}ms{3}", ANSI_YELLOW, algorithm, diff, ANSI_RESET));
    }

    public long getDiff() {
        return diff;
    }
}
