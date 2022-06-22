package com.guyan.thinkng.in.thread.atom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock 比读写锁更快的锁 JDK1.8提出来
 * 三种锁模式 写锁、悲观读锁、乐观锁
 */
public class Point {
    private int x, y;
    final StampedLock sl = new StampedLock();

    //计算到原点的距离
    int distanceFromOrigin() {
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入局部变量，
        // 读的过程数据可能被修改
        int curX = x, curY = y;
        //判断执行读操作期间，
        //是否存在写操作，如果存在，
        //则sl.validate返回false
        if(!sl.validate(stamp)) {
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return (int) Math.sqrt(curX * curX + curY * curY);
    }

    List<Integer> list = Collections.synchronizedList(new ArrayList<>());

}
