package com.iwmake.juc.c000;

import java.util.concurrent.TimeUnit;

/**
 * @author Dylan
 * @since 2020-10-17
 */
public class T01WhatIsThread {
    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + "==>T1");
            }
        }
    }

    public static void main(String[] args) {
        //new T1().run(); // 直接调用run方法，还是在父线程中执行，并没有启动一个新的线程
        new T1().start(); // start方法才会启动一个新的线程
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + "==>Main");
        }
    }
}
