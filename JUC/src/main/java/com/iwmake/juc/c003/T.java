package com.iwmake.juc.c003;

/**
 * Synchronized关键字对某个对象加锁
 * @author Dylan
 * @since 2020-10-17
 */
public class T {
    private int count = 10;
    //private Object o = new Object();

    // 在method上加锁相当于锁当前对象this，c002->T
    public synchronized void m() { // 等同于在方法代码执行时要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + ":count=" + count);
    }
}
