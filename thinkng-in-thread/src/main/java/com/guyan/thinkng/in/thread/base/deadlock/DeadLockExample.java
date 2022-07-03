package com.guyan.thinkng.in.thread.base.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试
 * 产生死锁的条件：
 * 1.互斥，共享资源 X 和 Y 同时只能被一个线程占有
 * 2.占有且等待，线程t1占有资源 X，同时请求占有资源 Y，会一直等待下去
 * 3.不可抢占，其它线程不能抢占线程t1占有的资源
 * 4.循环等待，线程 T1 等待线程 T2 占有的资源，线程 T2 等待线程 T1 占有的资源，就是循环等待。
 */
public class DeadLockExample {
    private final Object object1 = new Object();
    private final Object object2 = new Object();

    public void deadLock() {
        new Thread(() -> {
            synchronized (object1) {
                //睡眠2秒钟，避免线程t1抢占锁太快，无法形成死锁
                sleep(2);
                synchronized (object2) {
                    System.out.println(1);
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (object2) {
                synchronized (object1) {
                    System.out.println(2);
                }
            }
        }).start();
    }

    public void sleep(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DeadLockExample lockTest = new DeadLockExample();
        lockTest.deadLock();
    }
}
