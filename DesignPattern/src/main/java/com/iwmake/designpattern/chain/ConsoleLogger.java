package com.iwmake.designpattern.chain;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
