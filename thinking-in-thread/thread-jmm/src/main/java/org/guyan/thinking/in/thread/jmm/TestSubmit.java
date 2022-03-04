package org.guyan.thinking.in.thread.jmm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSubmit {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(() -> System.out.println(1));
    }
}
