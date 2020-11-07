package com.iwmake.designpattern.visitor;

/**
 * 访问者模式
 * @author Dylan
 * @since 2020-11-07
 */
public class VisitorPatternDemo {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
