package com.java.intro.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFactory {
    private static final ExecutorService executorService;

    static {
        executorService = Executors.newFixedThreadPool(5);
    }

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
