package com.iwmake.designpattern.observer;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
