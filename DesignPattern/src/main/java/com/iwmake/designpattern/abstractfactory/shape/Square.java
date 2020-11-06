package com.iwmake.designpattern.abstractfactory.shape;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method");
    }
}
