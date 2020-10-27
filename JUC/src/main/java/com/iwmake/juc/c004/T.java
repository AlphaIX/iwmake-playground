package com.iwmake.juc.c004;

/**
 * Synchronized关键字对某个对象加锁
 * @author Dylan
 * @since 2020-10-17
 */
public class T {
    private static int count = 10;
    //private Object o = new Object();

    // 静态方法上加synchronized，是对T.class对象加锁，等同于synchronized(T.class)
    public static synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + ":count=" + count);
    }

    public static void mm() {
        synchronized (T.class) { // 考虑一下这里synchronized(this) 是否可以
            count--;
        }
    }
}
