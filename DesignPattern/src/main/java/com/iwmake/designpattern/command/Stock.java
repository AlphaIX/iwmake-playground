package com.iwmake.designpattern.command;

/**
 * 请求类
 * @author Dylan
 * @since 2020-11-07
 */
public class Stock {
    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.printf("Stock [ Name: %s, Quantity: %d ] bought\n", name, quantity);
    }

    public void sell() {
        System.out.printf("Stock [ Name: %s, Quantity: %d ] sold\n", name, quantity);
    }
}
