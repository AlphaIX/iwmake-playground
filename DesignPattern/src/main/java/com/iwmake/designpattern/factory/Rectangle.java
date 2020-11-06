package com.iwmake.designpattern.factory;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method");
    }
}
