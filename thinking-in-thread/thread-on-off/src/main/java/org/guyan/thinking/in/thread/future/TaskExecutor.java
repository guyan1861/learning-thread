package org.guyan.thinking.in.thread.future;

import java.util.concurrent.*;

public class TaskExecutor {
    private final static ExecutorService taskExec = Executors.newFixedThreadPool(4);

    public static void timeRun(Runnable r) {
        Future<?> task = taskExec.submit(r);

        try {
            task.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            task.cancel(true);
            taskExec.shutdown();
        }
    }

    public static void main(String[] args) {
        timeRun(new Runnable() {
            public void run() {
                System.out.println("我是主线程");
            }
        });
    }
}
