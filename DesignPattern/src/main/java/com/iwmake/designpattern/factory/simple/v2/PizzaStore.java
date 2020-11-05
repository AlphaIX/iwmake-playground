package com.iwmake.designpattern.factory.simple.v2;

import com.iwmake.designpattern.factory.simple.v2.factory.SimpleFactory;
import com.iwmake.designpattern.factory.simple.v2.order.OrderPizza;
import com.iwmake.designpattern.factory.simple.v2.order.OrderPizza2;

/**
 * V2 使用简单工厂模式
 * <p>
 * 客户端，发出一个披萨订购任务
 * @author Dylan
 * @since 2020-11-05
 */
public class PizzaStore {
    public static void main(String[] args) {
//        // 使用简单工厂模式
//        new OrderPizza(new SimpleFactory());
//        System.out.println("~~退出程序~~");

        // 使用简单工厂模式2
        new OrderPizza2();
        System.out.println("~~退出程序~~");
    }
}
