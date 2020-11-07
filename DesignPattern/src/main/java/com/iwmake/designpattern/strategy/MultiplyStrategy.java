package com.iwmake.designpattern.strategy;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class MultiplyStrategy implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
