package com.guyan.thinkng.in.thread.base.synchronizedcase;

/**
 * 可见性表示：synchronized可以控制临界区的原子性、可见性，不保证有序性
 */
public class SafeCalc {
    static long value = 0L;

    synchronized long getValue() {
        return value;
    }

    synchronized void addOne() {
        value += 1;
    }

    public static void main(String[] args) throws InterruptedException {
        SafeCalc calc = new SafeCalc();

        Thread t1 = new Thread(calc::addOne);

        t1.start();
        t1.join();

        System.out.println(calc.getValue());
    }
}
