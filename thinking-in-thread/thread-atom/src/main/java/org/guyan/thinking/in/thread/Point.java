package org.guyan.thinking.in.thread;

import java.util.concurrent.locks.StampedLock;

public class Point {
    private int x;
    private int y;

    final StampedLock sl = new StampedLock();

    // 计算到原点的距离
    int distanceFromOrigin() {
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入局部变量
        // 读的过程数据可能被修改
        int curX = x;
        int curY = y;

        // 判断执行读操作期间
        // 是否存在写操作，如果存在，则sl.validate(stamp)返回fasle
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                // 释放悲观读锁
                sl.unlock(stamp);
            }
        }

        return (int) Math.sqrt(curX * curX + curY * curX);
    }
}
