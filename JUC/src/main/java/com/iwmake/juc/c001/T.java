package com.iwmake.juc.c001;

/**
 * Synchronized关键字对某个对象加锁
 * @author Dylan
 * @since 2020-10-17
 */
public class T {
    private int count = 10;
    private Object o = new Object();

    public void m() {
        synchronized (o) { // 任何执行下面的代码，必须先拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName() + ":count=" + count);
        }
    }
}
