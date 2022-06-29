package com.guyan.thinkng.in.thread.base.synchronizedcase;

/**
 * 多把锁锁多个资源，这些资源没有关联关系
 * 依照生活案例，修改密码和取款并行，互不打扰
 */
public class Account {
    private Integer balance;
    private final Object balLock = new Object();
    private String password;
    private final Object pwLock = new Object();

    public Account(Integer balance) {
        this.balance = balance;
    }

    //取款
    public void withdraw(Integer amount) {
        synchronized (balLock) {
            if(this.balance > amount) {
                this.balance -= amount;
            }
        }
    }

    //查询余额
    public Integer getBalance() {
        synchronized (balLock) {
            return this.balance;
        }
    }

    //修改密码
    public void updatePassword(String password) {
        synchronized (pwLock) {
            this.password = password;
        }
    }

    //获取密码
    String getPassword() {
        synchronized (pwLock) {
            return this.password;
        }
    }

    //这是并发有问题的操作，用一个锁保护了两个不相关的资源，没有互斥
    synchronized void transfer(Account target, int amount) {
        if(this.balance > amount) {
            this.balance -= amount;
            target.balance += amount;
        }
    }

    //修改上面的方式,这样保证了转账操作是安全的
    //这样的坏处，所有的转账都是串行，性能降低
    void transferSafe(Account target, int amount) {
        synchronized (Account.class) {
            if(this.balance > amount) {
                this.balance -= amount;
                target.balance += amount;
            }
        }
    }

    public static void main(String[] args) {
        Account accountA = new Account(100);
        Account accountB = new Account(100);

        accountA.withdraw(70);
        accountA.updatePassword("123");
        System.out.println(accountA.getBalance());
        System.out.println(accountA.getPassword());

        accountA.transferSafe(accountB, 20);
        System.out.println(accountA.getBalance());
        System.out.println(accountB.getBalance());
    }
}
