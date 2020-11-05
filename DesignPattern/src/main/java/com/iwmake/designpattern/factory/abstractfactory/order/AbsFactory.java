package com.iwmake.designpattern.factory.abstractfactory.order;

import com.iwmake.designpattern.factory.abstractfactory.pizza.Pizza;

/**
 * 一个抽象工厂模式的抽象层
 * @author Dylan
 * @since 2020-11-05
 */
public interface AbsFactory {
    // 让下面的工厂子类来 具体实现
    Pizza createPizza(String orderType);
}
