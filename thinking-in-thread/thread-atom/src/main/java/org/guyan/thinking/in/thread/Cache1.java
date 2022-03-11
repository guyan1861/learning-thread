package org.guyan.thinking.in.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache1<K, V> {
    final Map<K, V> map = new HashMap<>();

    final ReadWriteLock rwl = new ReentrantReadWriteLock();

    // 读锁
    final Lock r = rwl.readLock();

    // 写锁
    final Lock w = rwl.writeLock();

    // 读缓存
    V get(K key) {
        V v = null;
        r.lock();
        try {
            v = map.get(key);
        } finally {
            r.unlock();
        }
        if (v != null) {
            return v;
        }
        // 缓存中不存在，查询数据库
        w.lock();
        try {
            v = map.get(key);
            if (v == null) {
                // 查询写入代码
                map.put(key, v);
            }
        } finally {
            w.unlock();
        }
        return v;
    }

    // 写缓存
    void put(K key, V value) {
        w.lock();
        try {
            map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        ExecutorService pool = Executors.newFixedThreadPool(2);
        ExecutorService pool1 = Executors.newFixedThreadPool(2);
        Cache1<String, Integer> cache = new Cache1<>();
        for (Integer integer : list) {
            pool.execute(() -> {
                cache.put(integer.toString(), integer);
            });
        }

        for (Integer integer : list) {
            pool1.execute(() -> {
                System.out.println(cache.get(integer.toString()));
            });
        }
        try {

        } finally {
            pool.shutdown();
            pool1.shutdown();
        }
    }
}
