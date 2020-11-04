package com.iwmake.principle.singleresponsibility;

/**
 * 方案二 遵守了单一职责原则，
 * 但是改动很大（不但修改了修改类，也要修改调用端）
 * 改进 @see {@link SingleResponsibility03}
 * @author Dylan
 * @since 2020-11-04
 */
public class SingleResponsibility02 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("小汽车");

        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");

        WaterVehicle waterVehicle = new WaterVehicle();

    }

    static class RoadVehicle {
        public void run(String vehicle) {
            System.out.println(vehicle + "公路运行");
        }
    }

    static class AirVehicle {
        public void run(String vehicle) {
            System.out.println(vehicle + "天空运行");
        }
    }

    static class WaterVehicle {
        public void run(String vehicle) {
            System.out.println(vehicle + "水中运行");
        }
    }
}

