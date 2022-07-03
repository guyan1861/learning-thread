package com.guyan.thinkng.in.thread.base.visibility;

/**
 * 可见性问题和原子性问题
 */
public class CountExample {
    private long count = 0;

    //这个方法存在可见性问题和原子性问题
    public void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public long calc() throws InterruptedException {
        CountExample test = new CountExample();
        //创建两个线程，执行 add 操作
        Thread t1 = new Thread(() -> test.add10K());
        Thread t2 = new Thread(() -> test.add10K());
        //启动两个线程
        t1.start();
        t2.start();
        //等待两个线程结束
        t1.join();
        t2.join();
        return test.count;
    }

    public static void main(String[] args) throws InterruptedException {
        CountExample test = new CountExample();
        long count = test.calc();
        System.out.println(count);
    }
}
