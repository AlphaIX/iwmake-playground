package com.iwmake.designpattern.prototype;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
