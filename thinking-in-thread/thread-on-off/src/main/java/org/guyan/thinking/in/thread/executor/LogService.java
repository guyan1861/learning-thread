package org.guyan.thinking.in.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LogService {
    private final ExecutorService exec = Executors.newSingleThreadExecutor();

    public void start() {
    }

    public void stop() throws InterruptedException {
        try {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        } finally {
            //TODO
        }

    }
}
