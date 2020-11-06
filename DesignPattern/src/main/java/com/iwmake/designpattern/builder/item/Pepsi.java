package com.iwmake.designpattern.builder.item;

/**
 * 从ColdDrink冷饮扩展 --> 百事可乐
 * @author Dylan
 * @since 2020-11-06
 */
public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}
