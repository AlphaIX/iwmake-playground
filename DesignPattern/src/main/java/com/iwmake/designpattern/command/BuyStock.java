package com.iwmake.designpattern.command;

/**
 * 买股票 命令类 实现命令接口
 * @author Dylan
 * @since 2020-11-07
 */
public class BuyStock implements Order {

    private Stock abcStock;

    public BuyStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}
