package com.guyan.thinkng.in.thread.base.synchronizedcase;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程并发解决死锁问题
 */

public class SynchronizedResolveDeadLock {

    /**
     * 循环等待，一次性获取所有的资源解决死锁问题
     */
    class CycleWaitAccount {
        private int balance;

        /*allocator 应该为单例
         */
        private CycleWaitAllocator allocator = CycleWaitAllocator.getInstance();

        void transfer(CycleWaitAccount target, int amt) {
            // 一次性申请转出账户和转入账户，直到成功
            while (!allocator.apply(this, target)) ;
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
                allocator.free(this, target);
            }
        }
    }

    static class CycleWaitAllocator {
        private static volatile CycleWaitAllocator instance;
        private final List<Object> als = new ArrayList<>();

        private CycleWaitAllocator() {
        }

        /**
         * 一次性获取所有资源
         *
         * @param from
         * @param to
         * @return
         */
        public boolean apply(Object from, Object to) {
            synchronized (this) {
                /*
                    全部为 true 要返回 false，因为这时候锁被别人全部占用
                 */
                if(als.contains(from) || als.contains(to)) {
                    return false;
                }
                als.add(from);
                als.add(to);
                return true;
            }
        }

        /**
         * 释放梭鱼资源
         *
         * @param from
         * @param to
         */
        public synchronized void free(Object from, Object to) {
            als.remove(from);
            als.remove(to);
        }

        public static CycleWaitAllocator getInstance() {
            if(instance == null) {
                synchronized (CycleWaitAllocator.class) {
                    instance = new CycleWaitAllocator();
                }
            }
            return instance;
        }
    }

    /**
     * 用 wait()方式解决死锁问题
     */
    class WaitAndNotifyAccount {
        private int balance;

        //actor应该为单例
        private WaitAndNotifyAllocator actor;

        public WaitAndNotifyAccount(int balance) {
            this.balance = balance;
        }

        // 转账
        void transfer(WaitAndNotifyAccount target, int amt) throws InterruptedException {
            // 一次性申请转出账户和转入账户，直到成功
            actor.apply(this, target);
            if(this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
            actor.free(this, target);
        }
    }

    class WaitAndNotifyAllocator {
        private List<Object> als = new ArrayList<>();
        private WaitAndNotifyAllocator instance;

        private WaitAndNotifyAllocator() {
        }

        //通过等待的方式解决死锁问题
        public synchronized void apply(Object from, Object to) throws InterruptedException {
            while (als.contains(from) || als.contains(to)) {
                wait();
            }
            als.add(from);
            als.add(to);
        }

        public synchronized void free(Object from, Object to) {
            als.remove(from);
            als.remove(to);
            notifyAll();
        }
    }

    /**
     * 通过大小号的形式解决死锁问题
     */
    class SortAccount {
        private long id;
        private int balance;

        public SortAccount(int balance) {
            this.balance = balance;
        }

        /**
         * 始终保证先锁死小号，再锁死大号，破坏循环等待条件
         *
         * @param target
         * @param amt
         */
        public void transfer(SortAccount target, int amt) {
            SortAccount left = this;
            SortAccount right = target;
            if(left.id > right.id) {
                left = target;
                right = this;
            }
            //锁定转出账户
            synchronized (left) {
                //锁定转入账户
                synchronized (right) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

}
