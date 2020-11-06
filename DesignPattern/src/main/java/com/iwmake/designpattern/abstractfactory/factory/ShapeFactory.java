package com.iwmake.designpattern.abstractfactory.factory;

import com.iwmake.designpattern.abstractfactory.color.Color;
import com.iwmake.designpattern.abstractfactory.shape.Circle;
import com.iwmake.designpattern.abstractfactory.shape.Rectangle;
import com.iwmake.designpattern.abstractfactory.shape.Shape;
import com.iwmake.designpattern.abstractfactory.shape.Square;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        if (shape == null) {
            return null;
        }

        if (shape.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shape.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (shape.equalsIgnoreCase("square")) {
            return new Square();
        }

        return null;
    }
}
