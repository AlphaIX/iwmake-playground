package com.iwmake.designpattern.factory.simple.v2.pizza;

/**
 * 奶酪披萨
 * @author Dylan
 * @since 2020-11-05
 */
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备【奶酪】披萨原材料");
    }
}
