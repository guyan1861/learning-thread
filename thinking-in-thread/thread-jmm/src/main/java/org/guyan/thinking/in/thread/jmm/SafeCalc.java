package org.guyan.thinking.in.thread.jmm;

public class SafeCalc {
    private long value = 0;

    public synchronized long get() {
        return value;
    }

    public synchronized void addOne() {
        value += 1;
    }

    public static void main(String[] args) {
        SafeCalc calc = new SafeCalc();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                calc.addOne();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(calc.get() + Thread.currentThread().getName());
            }
        }).start();
    }
}
