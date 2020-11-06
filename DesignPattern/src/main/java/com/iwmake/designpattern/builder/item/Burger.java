package com.iwmake.designpattern.builder.item;

import com.iwmake.designpattern.builder.packing.Packing;
import com.iwmake.designpattern.builder.packing.Wrapper;

/**
 * 创建实现了Item接口的汉堡抽象类，该类提供了默认的功能
 * @author Dylan
 * @since 2020-11-06
 */
public abstract class Burger implements Item{

    @Override
    public Packing packing() {
        // 汉堡默认打包方式
        return new Wrapper();
    }

    // 抽象方法 由子类实现
    @Override
    public abstract float price();
}
