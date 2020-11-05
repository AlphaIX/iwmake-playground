package com.iwmake.designpattern.factory.factorymethod.order;

import com.iwmake.designpattern.factory.factorymethod.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 订购披萨
 * @author Dylan
 * @since 2020-11-05
 */
public abstract class OrderPizza {

    // 定义一个抽象方法，createPizza 让各个工厂子类自己实现
    abstract Pizza createPizza(String orderType);

    public OrderPizza() {
        Pizza pizza;
        String orderType;
        do {
            orderType = getType();
            // 调用子类
            pizza = createPizza(orderType); //工厂之类完成

            // 输出披萨制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }


    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza type:");
            String str = strin.readLine();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
