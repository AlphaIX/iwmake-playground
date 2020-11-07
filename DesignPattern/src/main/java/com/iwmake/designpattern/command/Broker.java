package com.iwmake.designpattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令调用类，股票经纪人
 * @author Dylan
 * @since 2020-11-07
 */
public class Broker {
    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
