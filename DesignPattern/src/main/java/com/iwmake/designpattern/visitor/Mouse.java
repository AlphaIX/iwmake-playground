package com.iwmake.designpattern.visitor;

/**
 * @author Dylan
 * @since 2020-11-07
 */
public class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}
