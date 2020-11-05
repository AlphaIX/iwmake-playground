package com.iwmake.designpattern.factory.factorymethod;


import com.iwmake.designpattern.factory.factorymethod.order.BJOrderPizza;
import com.iwmake.designpattern.factory.factorymethod.order.LDOrderPizza;

/**
 * 工厂方法模式
 * <p>
 * 客户端，发出一个披萨订购任务
 * @author Dylan
 * @since 2020-11-05
 */
public class PizzaStore {
    public static void main(String[] args) {
        // 创建北京口味pizza
        //new BJOrderPizza();

        // 创建伦敦口味pizza
        new LDOrderPizza();
    }
}
