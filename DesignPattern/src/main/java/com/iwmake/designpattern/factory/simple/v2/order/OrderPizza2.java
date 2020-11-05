package com.iwmake.designpattern.factory.simple.v2.order;

import com.iwmake.designpattern.factory.simple.v2.factory.SimpleFactory;
import com.iwmake.designpattern.factory.simple.v2.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 订购披萨 简单工厂模式 也可使用静态工厂模式
 * @author Dylan
 * @since 2020-11-05
 */
public class OrderPizza2 {

    Pizza pizza = null;
    String orderType = "";//用户输入的

    public OrderPizza2() {
        do {
            orderType = getType();
            pizza = SimpleFactory.createPizza2(orderType);
            // 输出pizza
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购披萨失败");
                break;
            }
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
