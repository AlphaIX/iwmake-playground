package com.iwmake.designpattern.factory.abstractfactory;


import com.iwmake.designpattern.factory.abstractfactory.order.BJFactory;
import com.iwmake.designpattern.factory.abstractfactory.order.LDFactory;
import com.iwmake.designpattern.factory.abstractfactory.order.OrderPizza;

/**
 * 抽象工厂模式
 * <p>
 * 客户端，发出一个披萨订购任务
 * @author Dylan
 * @since 2020-11-05
 */
public class PizzaStore {
    public static void main(String[] args) {
        //new OrderPizza(new BJFactory());
        new OrderPizza(new LDFactory());
    }
}
