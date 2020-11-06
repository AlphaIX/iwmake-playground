package com.iwmake.designpattern.builder.item;

/**
 * 从Burger扩展 --> 素食汉堡
 * @author Dylan
 * @since 2020-11-06
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
