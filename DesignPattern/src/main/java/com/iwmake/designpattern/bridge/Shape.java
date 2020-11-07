package com.iwmake.designpattern.bridge;

/**
 * 抽象类
 * @author Dylan
 * @since 2020-11-07
 */
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
