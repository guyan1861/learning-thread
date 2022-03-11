package org.guyan.thinking.in.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheData {
    Object data;

    volatile boolean cacheValid;

    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    Lock r = rwl.readLock();
    Lock w = rwl.writeLock();

    void processCachedData() {
        r.lock();

        if (!cacheValid) {
            r.unlock();

            w.lock();

            try {
                if (!cacheValid) {
                    //TODO  data =
                    cacheValid = true;
                }
                //    释放写锁前，可以降级为读锁
                //    降级是被允许的
                r.lock();
            } finally {
                w.unlock();
            }

            try {
                use(data);
            } finally {
                r.unlock();
            }
        }
    }

    private void use(Object data) {
    }
}
