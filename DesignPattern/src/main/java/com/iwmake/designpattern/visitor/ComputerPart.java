package com.iwmake.designpattern.visitor;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public interface ComputerPart {
    void accept(ComputerPartVisitor visitor);
}
