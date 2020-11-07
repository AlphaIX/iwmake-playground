package com.iwmake.designpattern.state;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class StopState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}
