package com.guyan.thinkng.in.thread.base.synchronizedcase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GuYan
 * @Time: 2022/7/3 14:14
 * @Description: 保证并发安全的例子
 **/
public class SynchronizedDemo {
    static class SafeCalc {
        static long value = 0L;

        synchronized long getValue() {
            return value;
        }

        synchronized void addOne() {
            value += 1;
        }

        public static void main(String[] args) throws InterruptedException {
            SafeCalc calc = new SafeCalc();

            List<Thread> ts = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Thread t = new Thread(() -> {
                    for (int j = 0; j < 100; j++) {
                        calc.addOne();
                    }
                });
                t.start();
                ts.add(t);
            }
            for (Thread t : ts) {
                t.join();
            }
            System.out.println(calc.getValue());
        }
    }
}
