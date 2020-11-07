package com.iwmake.designpattern.facade;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
