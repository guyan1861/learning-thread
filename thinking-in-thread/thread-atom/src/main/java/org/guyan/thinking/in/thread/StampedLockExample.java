package org.guyan.thinking.in.thread;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    final StampedLock sl = new StampedLock();


    void get() {
        long stamp = sl.readLock();
        try {
            //TODO
        } finally {
            sl.unlock(stamp);
        }
    }

    void put() {
        long stamp = sl.writeLock();
        try {
            //TODO
        } finally {
            sl.unlock(stamp);
        }
    }

}
