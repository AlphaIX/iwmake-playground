package com.iwmake.designpattern.interpreter;

/**
 * 一个表达式接口
 * @author Dylan
 * @since 2020-11-07
 */
public interface Expression {
    boolean interpret(String context);
}
