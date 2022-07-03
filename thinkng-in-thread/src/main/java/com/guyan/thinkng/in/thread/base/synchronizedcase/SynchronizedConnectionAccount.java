package com.guyan.thinkng.in.thread.base.synchronizedcase;

/**
 * @Author: GuYan
 * @Time: 2022/7/3 13:58
 * @Description: 锁定有关联关系的资源，注意锁的范围
 **/
public class SynchronizedConnectionAccount {
    private Integer balance;

    public SynchronizedConnectionAccount(Integer balance) {
        this.balance = balance;
    }

    //这是并发有问题的操作，用一个锁保护了两个不相关的资源，没有互斥
    synchronized void transfer(SynchronizedConnectionAccount target, int amount) {
        if(this.balance > amount) {
            this.balance -= amount;
            target.balance += amount;
        }
    }

    //修改上面的方式,这样保证了转账操作是安全的
    //这样的坏处，所有的转账都是串行，性能降低
    void transferSafe(SynchronizedConnectionAccount target, int amount) {
        synchronized (SynchronizedConnectionAccount.class) {
            if(this.balance > amount) {
                this.balance -= amount;
                target.balance += amount;
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedConnectionAccount accountA = new SynchronizedConnectionAccount(100);
        SynchronizedConnectionAccount accountB = new SynchronizedConnectionAccount(100);
        accountA.transferSafe(accountB, 20);
    }
}
