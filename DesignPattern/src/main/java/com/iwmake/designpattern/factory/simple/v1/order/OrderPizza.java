package com.iwmake.designpattern.factory.simple.v1.order;

import com.iwmake.designpattern.factory.simple.v1.pizza.CheesePizza;
import com.iwmake.designpattern.factory.simple.v1.pizza.GreekPizza;
import com.iwmake.designpattern.factory.simple.v1.pizza.PepperPizza;
import com.iwmake.designpattern.factory.simple.v1.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 订购披萨
 * @author Dylan
 * @since 2020-11-05
 */
public class OrderPizza {

    //构造器
    public OrderPizza() {
        Pizza pizza = null;
        String orderType;
        do {
            orderType = getType();
            if (orderType.equalsIgnoreCase("greek")) {
                pizza = new GreekPizza();
                pizza.setName("希腊披萨");
            } else if (orderType.equalsIgnoreCase("cheese")) {
                pizza = new CheesePizza();
                pizza.setName("奶酪披萨");
            }
            // 新增一个胡椒披萨，需要修改核心逻辑
            else if(orderType.equalsIgnoreCase("pepper")){
                pizza = new PepperPizza();
                pizza.setName("胡椒披萨");
            }
            else {
                break;
            }
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
