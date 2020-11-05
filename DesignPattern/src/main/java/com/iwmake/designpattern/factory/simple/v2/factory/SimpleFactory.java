package com.iwmake.designpattern.factory.simple.v2.factory;

import com.iwmake.designpattern.factory.simple.v2.pizza.*;

/**
 * V2版本：使用简单工厂模式改进V1
 * 每次新增披萨种类，只需对工厂类进行修改即可
 * <p>
 * 简单工厂类
 * @author Dylan
 * @since 2020-11-05
 */
public class SimpleFactory {

    // 更加orderType 返回对应的pizza
    public Pizza createPizza(String orderType) {
        System.out.println("使用简单工厂模式==>");
        Pizza pizza = null;
        if (orderType.equalsIgnoreCase("greek")) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if (orderType.equalsIgnoreCase("cheese")) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        } else if (orderType.equalsIgnoreCase("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        // 增加一个中国披萨种类，只需修改本工厂类即可
        else if(orderType.equalsIgnoreCase("china")){
            pizza = new ChinaPizza();
            pizza.setName("中国披萨");
        }

        return pizza;
    }

    // 简单工厂模式也叫静态工厂模式
    public static Pizza createPizza2(String orderType) {
        System.out.println("使用简单工厂模式2==>");
        Pizza pizza = null;
        if (orderType.equalsIgnoreCase("greek")) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if (orderType.equalsIgnoreCase("cheese")) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        } else if (orderType.equalsIgnoreCase("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        // 增加一个中国披萨种类，只需修改本工厂类即可
        else if(orderType.equalsIgnoreCase("china")){
            pizza = new ChinaPizza();
            pizza.setName("中国披萨");
        }

        return pizza;
    }
}
