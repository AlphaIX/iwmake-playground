package com.iwmake.designpattern.chain;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
