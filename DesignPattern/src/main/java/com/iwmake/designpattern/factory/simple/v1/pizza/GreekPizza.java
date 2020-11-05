package com.iwmake.designpattern.factory.simple.v1.pizza;

/**
 * 希腊披萨
 * @author Dylan
 * @since 2020-11-05
 */
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备【希腊】披萨原材料");
    }
}
