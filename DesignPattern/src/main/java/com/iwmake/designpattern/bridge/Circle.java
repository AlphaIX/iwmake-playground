package com.iwmake.designpattern.bridge;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        // 桥接
        drawAPI.drawCircle(radius, x, y);
    }
}
