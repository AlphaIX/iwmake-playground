package com.iwmake.designpattern.factory.simple.v2.pizza;

/**
 * 再新增一个中国披萨
 * @author Dylan
 * @since 2020-11-05
 */
public class ChinaPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("准备【中国披萨】原材料");
    }
}
