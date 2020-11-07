package com.iwmake.designpattern.strategy;

/**
 * 策略模式
 * @author Dylan
 * @since 2020-11-07
 */
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new AddStrategy());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new SubtractStrategy());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new MultiplyStrategy());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
