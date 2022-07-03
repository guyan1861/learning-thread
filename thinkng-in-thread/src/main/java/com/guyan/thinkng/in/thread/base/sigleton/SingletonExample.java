package com.guyan.thinkng.in.thread.base.sigleton;

/**
 * 单例模式错误写法
 */
public class SingletonExample {
    //正确的方式应该加上 volatile 修饰，避免指令重排
    private static SingletonExample instance;

    private SingletonExample() {
    }


    //这个方式返回单例对象，在多线程情况下存在有序性问题
    /*
        出在 new 操作上，我们以为的 new 操作应该是：
          分配一块内存 M；
          在内存 M 上初始化 Singleton 对象；
          然后 M 的地址赋值给 instance 变量。

        实际过程可能被优化为：
          分配一块内存 M；
          然后 M 的地址赋值给 instance 变量
          在内存 M 上初始化 Singleton 对象；

          这个时候得到的单例对象会报 null 错误
     */
    public static SingletonExample getInstance() {
        if(instance == null) {
            synchronized (SingletonExample.class) {
                if(instance == null) {
                    instance = new SingletonExample();
                }
            }
        }
        return instance;
    }
}
