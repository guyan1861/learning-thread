package com.guyan.thinkng.in.thread.base;

public class SafeCalc {
    static long value = 0L;

    synchronized long getValue() {
        return value;
    }

    synchronized void addOne() {
        value += 1;
    }
}
