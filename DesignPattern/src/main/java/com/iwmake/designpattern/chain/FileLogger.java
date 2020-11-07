package com.iwmake.designpattern.chain;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
