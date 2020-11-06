package com.iwmake.designpattern.factory;

/**
 * @author Dylan
 * @since 2020-11-06
 */
public class ShapeFactory {
    /**
     * 获取给定信息的类型对象
     * @param shapeType
     * @return
     */
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }

        if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("square")) {
            return new Square();
        }

        return null;
    }
}
