package org.guyan.thinking.in.thread.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestTaskExecutorWork {
    private static final int NTHREADS = 8;

    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Runnable task = new Runnable() {
                public void run() {
                    System.out.println("I am " + Thread.currentThread().getName() + " working ~~~~");
                }
            };
            exec.execute(task);
        }
    }
}

