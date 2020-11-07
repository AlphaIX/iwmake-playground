package com.iwmake.designpattern.facade;

/**
 * 外观模式
 * @author Dylan
 * @since 2020-11-07
 */
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
