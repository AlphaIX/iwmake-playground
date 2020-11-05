package com.iwmake.designpattern.factory.abstractfactory.pizza;

/**
 * 披萨抽象类
 * @author Dylan
 * @since 2020-11-05
 */
public abstract class Pizza {
    protected String name;

    public abstract void prepare();

    public void bake() {
        System.out.println(name + " baking");
    }

    public void cut() {
        System.out.println(name + " cutting");
    }

    public void box() {
        System.out.println(name + " boxing");
    }

    public void setName(String name) {
        this.name = name;
    }
}
