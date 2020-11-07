package com.iwmake.designpattern.decorator;

/**
 * 创建Shape接口的抽象装饰类
 * @author Dylan
 * @since 2020-11-07
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}
