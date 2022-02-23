package org.guyan.thinking.in.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestTaskExecutorServiceWork {
    private static final int NTHREADS = 8;

    private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws InterruptedException {

        while (!exec.isShutdown()) {
            try {
                Runnable task = new Runnable() {
                    public void run() {
                        System.out.println("I am " + Thread.currentThread().getName() + " working ~~~~");
                    }
                };
                exec.execute(task);
            } catch (Exception e) {
                if (!exec.isShutdown()) {
                    System.out.println("执行任务异常！");
                }
                exec.shutdown();
                System.out.println(e.getMessage());
            } finally {
                exec.shutdown();
                System.out.println("线程池执行完毕！");
            }

        }
    }
}

