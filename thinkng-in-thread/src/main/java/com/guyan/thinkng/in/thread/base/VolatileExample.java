package com.guyan.thinkng.in.thread.base;

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

    public static void main(String[] args) {

    }
}
