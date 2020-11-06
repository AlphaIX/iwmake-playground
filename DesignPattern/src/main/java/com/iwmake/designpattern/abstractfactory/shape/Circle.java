package com.iwmake.designpattern.abstractfactory.shape;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method");
    }
}
