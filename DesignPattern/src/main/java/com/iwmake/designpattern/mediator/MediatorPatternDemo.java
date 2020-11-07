package com.iwmake.designpattern.mediator;

/**
 * 中介者模式
 * @author Dylan
 * @since 2020-11-07
 */
public class MediatorPatternDemo {
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
