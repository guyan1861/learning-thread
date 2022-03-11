package org.guyan.thinking.in.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        final ExecutorService service = Executors.newFixedThreadPool(1);

        final CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            service.execute(() -> {
                System.out.println("我是第三个完成的");
            });

            service.shutdown();
        });

        Thread t1 = new Thread(() -> {
            System.out.println("第一个线程在执行！------------------");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            System.out.println("第二个线程在执行！------------------");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t2.start();

    }
}
