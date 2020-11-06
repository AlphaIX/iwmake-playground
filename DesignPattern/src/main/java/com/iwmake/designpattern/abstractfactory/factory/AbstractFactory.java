package com.iwmake.designpattern.abstractfactory.factory;

import com.iwmake.designpattern.abstractfactory.color.Color;
import com.iwmake.designpattern.abstractfactory.shape.Shape;

/**
 * 为Color和Shape创建抽象类来获取工厂
 * @author Dylan
 * @since 2020-11-06
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
