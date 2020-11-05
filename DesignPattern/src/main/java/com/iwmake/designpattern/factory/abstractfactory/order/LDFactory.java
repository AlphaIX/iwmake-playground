package com.iwmake.designpattern.factory.abstractfactory.order;

import com.iwmake.designpattern.factory.abstractfactory.pizza.*;

/**
 * @author Dylan
 * @since 2020-11-05
 */
public class LDFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("使用抽象工厂模式~~~");
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
            pizza.setName("伦敦奶酪披萨");
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
            pizza.setName("伦敦胡椒披萨");
        }
        return pizza;
    }
}
