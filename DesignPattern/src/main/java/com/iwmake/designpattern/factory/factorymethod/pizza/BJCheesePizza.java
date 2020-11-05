package com.iwmake.designpattern.factory.factorymethod.pizza;

/**
 * @author Dylan
 * @since 2020-11-05
 */
public class BJCheesePizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("【北京奶酪披萨】准备原材料");
    }
}
