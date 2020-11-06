package com.iwmake.designpattern.prototype;

/**
 * 原型模式
 * @author Dylan
 * @since 2020-11-06
 */
public class PrototypePatternDemo {
    public static void main(String[] args) {

        ShapeCache.loadCache();

        Shape cloneShape = ShapeCache.getShape("1");
        System.out.println("Shape: " + cloneShape.getType());

        Shape cloneShape1 = ShapeCache.getShape("2");
        System.out.println("Shape: " + cloneShape1.getType());

        Shape cloneShape2 = ShapeCache.getShape("3");
        System.out.println("Shape: " + cloneShape2.getType());
    }
}
