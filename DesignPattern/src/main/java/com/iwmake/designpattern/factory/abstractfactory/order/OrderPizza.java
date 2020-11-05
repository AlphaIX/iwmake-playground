package com.iwmake.designpattern.factory.abstractfactory.order;

import com.iwmake.designpattern.factory.abstractfactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 订购披萨
 * @author Dylan
 * @since 2020-11-05
 */
public class OrderPizza {

    AbsFactory factory;

    public OrderPizza(AbsFactory factory) {
        setAbsFactory(factory);
    }

    private void setAbsFactory(AbsFactory absFactory) {
        Pizza pizza = null;
        String orderType = "";// 用户输入
        this.factory = absFactory;
        do {
            orderType = getType();
            pizza = factory.createPizza(orderType);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购失败");
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
