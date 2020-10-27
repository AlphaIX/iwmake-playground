package com.iwmake.juc.c000;

import java.util.concurrent.Callable;

/**
 * @author Dylan
 * @since 2020-10-17
 */
public class T02HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + "==>Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + "==>Hello MyRunnable!");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + "==>Hello Lambda!");
        }).start();
    }
    // 创建线程的方式：本质上2种，一、继承Thread类重新run方法。二、实现Runnable接口重新run方法
    // 其他方式都是居于以上的变体或封装如：lambda方式，线程池方式等
    // 注：Thread是Runnable接口的一个实现
}
