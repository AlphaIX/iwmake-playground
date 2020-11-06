package com.iwmake.designpattern.abstractfactory.color;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
