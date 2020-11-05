package com.iwmake.designpattern.factory.simple.v1;

import com.iwmake.designpattern.factory.simple.v1.order.OrderPizza;

/**
 * V1版本：未使用设计模式，违反OCP原则，
 * 当新增加一个pizza种类时，{@link OrderPizza}也要做相应的修改
 *
 * 客户端，发出一个披萨订购任务
 * @author Dylan
 * @since 2020-11-05
 */
public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza();
    }
}
