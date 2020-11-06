package com.iwmake.designpattern.abstractfactory.factory;

import com.iwmake.designpattern.abstractfactory.factory.AbstractFactory;
import com.iwmake.designpattern.abstractfactory.factory.ColorFactory;
import com.iwmake.designpattern.abstractfactory.factory.ShapeFactory;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}
