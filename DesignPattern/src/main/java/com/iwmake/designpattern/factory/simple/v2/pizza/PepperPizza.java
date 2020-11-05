package com.iwmake.designpattern.factory.simple.v2.pizza;

import com.iwmake.designpattern.factory.simple.v2.order.OrderPizza;

/**
 * 新增加一个胡椒pizza，{@link OrderPizza}也要做相应修改，改动较大
 * @author Dylan
 * @since 2020-11-05
 */
public class PepperPizza  extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备【胡椒】披萨原材料");
    }
}
