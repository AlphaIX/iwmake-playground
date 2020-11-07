package com.iwmake.designpattern.facade;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
