package org.guyan.thinking.in.thread;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class ObjPool<T, R> {

    // 用信号量实现限流器
    final List<T> pool;

    // 构造函数
    final Semaphore semaphore;

    public ObjPool(int size, T t) {
        pool = new Vector<T>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        semaphore = new Semaphore(size);
    }

    // 利用对象池的对象，调用func
    R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        semaphore.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ObjPool<String, String> pool = new ObjPool<String, String>(10, "2");

        pool.exec(new Function<String, String>() {
            @Override
            public String apply(String t) {
                System.out.println(t);
                return t.toString();
            }
        });

    }
}
