package com.iwmake.designpattern.factory.abstractfactory.pizza;

/**
 * @author Dylan
 * @since 2020-11-05
 */
public class LDCheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("【伦敦奶酪披萨】准备原材料");
    }
}
