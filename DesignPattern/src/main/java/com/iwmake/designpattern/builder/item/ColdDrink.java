package com.iwmake.designpattern.builder.item;

import com.iwmake.designpattern.builder.packing.Bottle;
import com.iwmake.designpattern.builder.packing.Packing;

/**
 * 创建实现了Item接口的 冷饮 抽象类，该类提供了默认的功能
 * @author Dylan
 * @since 2020-11-06
 */
public abstract class ColdDrink implements Item{

    @Override
    public Packing packing() {
        // 默认使用瓶子打包
        return new Bottle();
    }

    // 抽象方法 由子类实现
    @Override
    public abstract float price();
}
