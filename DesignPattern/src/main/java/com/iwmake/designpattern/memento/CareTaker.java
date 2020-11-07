package com.iwmake.designpattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
