package com.iwmake.designpattern.factory.factorymethod.order;

import com.iwmake.designpattern.factory.factorymethod.pizza.BJCheesePizza;
import com.iwmake.designpattern.factory.factorymethod.pizza.BJPepperPizza;
import com.iwmake.designpattern.factory.factorymethod.pizza.Pizza;

/**
 * @author Dylan
 * @since 2020-11-05
 */
public class BJOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
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
