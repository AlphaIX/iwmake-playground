package com.iwmake.principle.ocp;

/**
 * 违反 开闭原则 的一个案例
 * 优点：容易理解，简单易操作
 * 缺点：违反OCP原则，即对扩展开放(提供方)，对修改关闭(使用方)，如果我们增加新功能，尽量不修改代码，或者尽可能少得修改代码
 * 本案例中：如果我们要增加一个图形种类，修改的地方太多
 * 改进：遵守OCP原则 @see {@link Ocp02}
 * @author Dylan
 * @since 2020-11-04
 */
public class Ocp01 {
    public static void main(String[] args) {
        // 使用看看存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());

        // 增加了一个三角形，导致使用方 新加入代码
        graphicEditor.drawShape(new Triangle());

    }

    // 一个用于绘图的类
    static class GraphicEditor {
        public void drawShape(Shape s) {
            if (s.type == 1) {
                drawRectangle(s);
            } else if (s.type == 2) {
                drawCircle(s);
            }
            // 增加了一个三角形，导致使用方 新加入代码
            else if(s.type==3){
                drawTriangle(s);
            }
        }

        public void drawRectangle(Shape s) {
            System.out.println("矩形");
        }

        public void drawCircle(Shape s) {
            System.out.println("圆形");
        }

        /**
         * 增加了一个三角形，导致使用方 新加入代码
         * @param s
         */
        public void drawTriangle(Shape s) {
            System.out.println("三角形");
        }
    }

    // 基类
    static class Shape {
        int type;
    }

    static class Rectangle extends Shape {
        Rectangle() {
            super.type = 1;
        }
    }

    static class Circle extends Shape {
        Circle() {
            super.type = 2;
        }
    }

    /**
     * 新增一个三角形
     */
    static class Triangle extends Shape {
        Triangle() {
            super.type = 3;
        }
    }
}
