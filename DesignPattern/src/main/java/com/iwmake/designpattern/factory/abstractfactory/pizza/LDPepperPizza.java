package com.iwmake.designpattern.factory.abstractfactory.pizza;

/**
 * @author Dylan
 * @since 2020-11-05
 */
public class LDPepperPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("【伦敦胡椒披萨】准备原材料");
    }
}
