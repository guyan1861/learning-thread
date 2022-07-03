package com.guyan.thinkng.in.thread.base.synchronizedcase;

/**
 * 多把锁锁多个资源，这些资源没有关联关系
 * 依照生活案例，修改密码和取款并行，互不打扰
 */
public class SynchronizedNoConnectionAccount {
    private Integer balance;
    private final Object balLock = new Object();
    private String password;
    private final Object pwLock = new Object();

    public SynchronizedNoConnectionAccount(Integer balance) {
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


    public static void main(String[] args) {
        SynchronizedNoConnectionAccount accountA = new SynchronizedNoConnectionAccount(100);
        accountA.withdraw(70);
        accountA.updatePassword("123");
        System.out.println(accountA.getBalance());
        System.out.println(accountA.getPassword());

    }
}
