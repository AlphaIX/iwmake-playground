package com.iwmake.designpattern.factory.abstractfactory.order;

import com.iwmake.designpattern.factory.abstractfactory.pizza.BJCheesePizza;
import com.iwmake.designpattern.factory.abstractfactory.pizza.BJPepperPizza;
import com.iwmake.designpattern.factory.abstractfactory.pizza.Pizza;

/**
 * @author Dylan
 * @since 2020-11-05
 */
public class BJFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("使用抽象工厂模式~~~");
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
            pizza.setName("北京奶酪披萨");
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
            pizza.setName("北京胡椒披萨");
        }
        return pizza;
    }
}
