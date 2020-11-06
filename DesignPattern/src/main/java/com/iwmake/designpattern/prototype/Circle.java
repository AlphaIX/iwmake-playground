package com.iwmake.designpattern.prototype;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
