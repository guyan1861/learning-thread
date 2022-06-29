package com.guyan.thinkng.in.thread.base.visibility;

/**
 * 可见性测试入门用例
 */
public class VolatileExample {
    int x = 0;
    volatile boolean v = false;

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if(v) {
            System.out.println(x);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileExample ve = new VolatileExample();
        Thread t1 = new Thread(ve::writer, "t1");
        t1.start();
        t1.join();
        ve.reader();
    }
}
