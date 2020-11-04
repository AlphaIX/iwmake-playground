package com.iwmake.principle.ocp;

/**
 * 遵守OCP原则
 * 改进思路：把Shape做成一个抽象类，并提供一个抽象方法draw,让子类去实现即可
 * 这样我们有新的图形类时，只需要继承Shape，并实现draw方法即可，使用方的代码，不需要修改==>满足开闭原则
 * @author Dylan
 * @since 2020-11-04
 */
public class Ocp02 {
    public static void main(String[] args) {

        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Triangle());
        // 新增一个其他图形graphicEditor使用方不用做任何修改
        graphicEditor.drawShape(new Other());

    }

    // 一个用于绘图的类
    static class GraphicEditor {
        public void drawShape(Shape s) {
            s.draw();
        }
    }

    // 基类--> 做成抽象类
    static abstract class Shape {
        int type;

        abstract void draw();// 抽象方法
    }

    static class Rectangle extends Shape {
        Rectangle() {
            super.type = 1;
        }

        @Override
        void draw() {
            System.out.println("矩形");
        }
    }

    static class Circle extends Shape {
        Circle() {
            super.type = 2;
        }

        @Override
        void draw() {
            System.out.println("圆形");
        }
    }

    /**
     * 三角形
     */
    static class Triangle extends Shape {
        Triangle() {
            super.type = 3;
        }

        @Override
        void draw() {
            System.out.println("三角形");
        }
    }

    /**
     * 新增一个其他图形
     */
    static class Other extends Shape {
        Other() {
            super.type = 4;
        }

        @Override
        void draw() {
            System.out.println("其他图形");
        }
    }
}
