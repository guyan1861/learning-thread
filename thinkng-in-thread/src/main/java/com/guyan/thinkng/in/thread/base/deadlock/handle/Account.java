package com.guyan.thinkng.in.thread.base.deadlock.handle;

/**
 * 解决转账死锁：
 * 1.破坏占有且等待条件
 */
public class Account {
    private int balance;

    private final Allocator actor = Allocator.getInstance();

    public Account(int balance) {
        this.balance = balance;
    }

    // 转账
    void transfer(Account target, int amt) {
        // 一次性申请转出账户和转入账户，直到成功
        while (!actor.apply(this, target)) {

        }
        try {
            // 锁定转出账户
            synchronized (this) {
                // 锁定转入账户
                synchronized (target) {
                    if(this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actor.free(this, target);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account accountA = new Account(100);
        Account accountB = new Account(100);

        Thread t1 = new Thread(() -> accountA.transfer(accountB, 30));
        Thread t2 = new Thread(() -> accountB.transfer(accountA, 50));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(accountA.balance);
        System.out.println(accountB.balance);
    }
}

