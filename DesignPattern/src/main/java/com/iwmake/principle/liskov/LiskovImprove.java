package com.iwmake.principle.liskov;

/**
 * 里氏替换原则
 * @author Dylan
 * @since 2020-11-04
 */
public class LiskovImprove {
    public static void main(String[] args) {

        A a = new A();
        System.out.printf("11-3=%d \n", a.func1(11, 3));
        System.out.printf("1-8=%d \n", a.func1(1, 8));

        System.out.println("------------------------");
        B b = new B();
        // 因为B不再继承A, B中的方法使用可以很明确 func1不再是求减法
        System.out.printf("11+3=%d \n", b.func1(11, 3));
        System.out.printf("1+8=%d \n", b.func1(1, 8));
        System.out.printf("11+3+9=%d \n", b.func2(11, 3));
        // 使用组合仍然可以使用A中的方法
        System.out.printf("11-3=%d \n", b.func3(11, 3));

    }

    // 创建一个更加基础的类
    static class Base {
        // 把更加基础的方法和成员放到基类中

    }

    static class A extends Base {
        public int func1(int num1, int num2) {
            return num1 - num2;
        }
    }

    static class B extends Base {
        // 如果B需要使用A类方法，使用组合关系
        private A a = new A();

        public int func1(int a, int b) {
            return a + b;
        }

        public int func2(int a, int b) {
            return func1(a, b) + 9;
        }

        // 使用A中的方法
        public int func3(int a, int b) {
            return this.a.func1(a, b);
        }
    }
}
