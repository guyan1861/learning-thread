package org.guyan.thinking.in.thread.interrupt;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    private static final ScheduledExecutorService cancelExec
            = Executors.newScheduledThreadPool(4);

    public static void timeRun(Runnable r) {
        final Thread thread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            public void run() {
                thread.interrupt();
            }
        }, 1, TimeUnit.SECONDS);
        r.run();
    }

    public static void main(String[] args) {
        timeRun(new Runnable() {
            public void run() {
                System.out.println("我是主线程");
            }
        });
    }
}
