package com.iwmake.designpattern.abstractfactory.factory;

import com.iwmake.designpattern.abstractfactory.color.Blue;
import com.iwmake.designpattern.abstractfactory.color.Color;
import com.iwmake.designpattern.abstractfactory.color.Green;
import com.iwmake.designpattern.abstractfactory.color.Red;
import com.iwmake.designpattern.abstractfactory.shape.Shape;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }

        if (color.equalsIgnoreCase("red")) {
            return new Red();
        } else if (color.equalsIgnoreCase("green")) {
            return new Green();
        } else if (color.equalsIgnoreCase("blue")) {
            return new Blue();
        }

        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
