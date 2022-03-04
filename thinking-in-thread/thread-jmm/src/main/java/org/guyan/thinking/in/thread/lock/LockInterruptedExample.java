package org.guyan.thinking.in.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class LockInterruptedExample {

    public static void main(String[] args) {
        LinkedBlockingQueue<List<Integer>> objects = new LinkedBlockingQueue<>();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
            try {
                objects.put(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 10; i < 20; i++) {
                list.add(i);
            }
            try {
                objects.put(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println(objects);
    }
}
