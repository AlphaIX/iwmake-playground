package com.iwmake.principle.singleresponsibility;

/**
 * 方案三
 * 对方案一没有做大的修改，只是增加方法
 * 虽然在类级别没有遵守单一职责原则， 但是在方法级别遵守了单一职责原则
 * @author Dylan
 * @since 2020-11-04
 */
public class SingleResponsibility03 {
    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.runWater("轮船");
        vehicle.runAir("飞机");

    }

    static class Vehicle {

        public void run(String vehicle) {
            System.out.println(vehicle + "在公路上运行...");
        }

        public void runAir(String vehicle) {
            System.out.println(vehicle + "在天空上运行...");
        }

        public void runWater(String vehicle) {
            System.out.println(vehicle + "在水中运行...");
        }

    }
}
