package com.iwmake.designpattern.state;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}
