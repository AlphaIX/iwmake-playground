package com.iwmake.designpattern.builder.packing;

/**
 * 盒装
 * @author Dylan
 * @since 2020-11-06
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
