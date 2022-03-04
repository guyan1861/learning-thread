package org.guyan.thinking.in.thread.readbigfile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadBigFile {
    public static void main(String[] args) {
        int total = 100000000;
        int segment = 20;

        ExecutorService pool = Executors.newFixedThreadPool(segment);
        AtomicInteger incr = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(segment);

        long s = System.currentTimeMillis();

        for (int i = 0; i <segment ; i++) {
            pool.execute(
                    ()->{

                    }
            );
        }

    }
}
