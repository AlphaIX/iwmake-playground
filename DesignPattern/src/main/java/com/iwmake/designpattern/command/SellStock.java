package com.iwmake.designpattern.command;

/**
 * 卖股票 命令类 实现命令接口
 * @author Dylan
 * @since 2020-11-07
 */
public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}
