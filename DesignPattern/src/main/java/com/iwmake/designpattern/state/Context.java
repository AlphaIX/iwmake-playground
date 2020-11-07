package com.iwmake.designpattern.state;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class Context {
    private State state;

    public Context() {
        state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
