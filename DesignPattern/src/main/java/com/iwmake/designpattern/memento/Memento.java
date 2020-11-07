package com.iwmake.designpattern.memento;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
