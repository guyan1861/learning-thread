package org.guyan.thinking.in.thread;

import java.util.Queue;

public class SemaphoreExample {
    // 计数器
    int count;
    // 等待队列
    Queue queue;

    public SemaphoreExample(int count) {
        this.count = count;
    }

    private void down() {
        this.count--;
        if (this.count < 0) {
            //将当前线程插入等待队列
            //阻塞当前线程
        }
    }

    void up() {
        this.count++;
        if (this.count <= 0) {
            //移除等待队列中的某个线程T
            //唤醒线程T
        }
    }
}
