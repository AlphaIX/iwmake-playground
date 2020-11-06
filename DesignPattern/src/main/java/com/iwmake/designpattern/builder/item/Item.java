package com.iwmake.designpattern.builder.item;

import com.iwmake.designpattern.builder.packing.Packing;

/**
 * 表示食物条目的接口
 * @author Dylan
 * @since 2020-11-06
 */
public interface Item {
    String name();
    float price();
    Packing packing();
}
