package org.guyan.thinking.in.thread.jmm;

public class VolatileExample {
    private int x = 0;

    public void writer() {
        x = 42;
    }

    public void reader() {
        System.out.println(x);
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileExample example = new VolatileExample();
        Thread thread = new Thread(() -> {
            example.writer();
        });
        thread.start();
        thread.join();
        System.out.println(example.x);
    }

}
