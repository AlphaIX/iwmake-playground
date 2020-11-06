package com.iwmake.designpattern.prototype;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class Square extends Shape {
    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
