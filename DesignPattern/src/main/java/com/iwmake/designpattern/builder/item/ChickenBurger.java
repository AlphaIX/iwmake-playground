package com.iwmake.designpattern.builder.item;

/**
 * 从Burger扩展 --> 鸡肉汉堡
 * @author Dylan
 * @since 2020-11-06
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
