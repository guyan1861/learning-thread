package com.guyan.thinkng.in.thread.base.deadlock.handle;

import java.util.ArrayList;
import java.util.List;

public class Allocator {
    private static volatile Allocator instance;
    private final List<Object> als = new ArrayList<>();

    private Allocator() {
    }

    public static Allocator getInstance() {
        if(instance == null) {
            synchronized (Allocator.class) {
                if(instance == null) {
                    instance = new Allocator();
                }
            }
        }
        return instance;
    }

    public boolean apply(Object from, Object to) {
        synchronized (this) {
            if(als.contains(from) || als.contains(to)) {
                return false;
            }
            als.add(from);
            als.add(to);
            return true;
        }
    }

    public synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }
}
