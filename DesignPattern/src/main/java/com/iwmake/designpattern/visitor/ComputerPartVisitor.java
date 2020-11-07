package com.iwmake.designpattern.visitor;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public interface ComputerPartVisitor {
    void visit(Computer computer);
    void visit(Mouse mouse);
    void visit(Keyboard keyboard);
    void visit(Monitor monitor);
}
