package com.iwmake.designpattern.builder.item;

/**
 * 从ColdDrink冷饮扩展 --> 可口可乐
 * @author Dylan
 * @since 2020-11-06
 */
public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
