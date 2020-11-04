package com.iwmake.principle.singleresponsibility;

/**
 * 方案一：违反单一职责原则的示例
 * @author Dylan
 * @since 2020-11-04
 */
public class SingleResponsibility01 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("小汽车");
        vehicle.run("飞机");
    }

    /**
     * 交通工具类【违反单一职责原则】
     * // 解决方式：
     * 根据交通工具运行方法不同，拆分成不同的类即可
     * @see {@link SingleResponsibility02}
     */
    static class Vehicle {

        public void run(String vehicle) {
            System.out.println(vehicle + "在公路上运行...");
        }

    }
}
