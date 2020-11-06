package com.iwmake.designpattern.abstractfactory;

import com.iwmake.designpattern.abstractfactory.color.Color;
import com.iwmake.designpattern.abstractfactory.factory.AbstractFactory;
import com.iwmake.designpattern.abstractfactory.factory.FactoryProducer;
import com.iwmake.designpattern.abstractfactory.shape.Shape;

/**
 * 抽象工厂模式
 * @author Dylan
 * @since 2020-11-06
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("使用形状工厂");
        // 获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        // 获取形状为Circle的对象，并调用其draw方法
        Shape shape1 = shapeFactory.getShape("circle");
        shape1.draw();
        // 获取形状为Rectangle的对象，并调用其draw方法
        Shape shape2 = shapeFactory.getShape("rectangle");
        shape2.draw();
        // 获取形状为Square的对象，并调用其draw方法
        Shape shape3 = shapeFactory.getShape("square");
        shape3.draw();

        System.out.println("====================");
        System.out.println("使用颜色工厂");
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
        Color color1 = colorFactory.getColor("red");
        color1.fill();
        Color color2 = colorFactory.getColor("green");
        color2.fill();
        Color color3 = colorFactory.getColor("blue");
        color3.fill();

    }
}
