package com.iwmake.designpattern.factory.factorymethod.order;

import com.iwmake.designpattern.factory.factorymethod.pizza.*;

/**
 * @author Dylan
 * @since 2020-11-05
 */
public class LDOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String orderType) {
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
