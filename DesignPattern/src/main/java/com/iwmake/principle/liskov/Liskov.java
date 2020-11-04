package com.iwmake.principle.liskov;

/**
 * 不小心重写父类方法，造成错误bug
 * 改进 @see {@link LiskovImprove}
 * @author Dylan
 * @since 2020-11-04
 */
public class Liskov {
    public static void main(String[] args) {

        A a = new A();
        System.out.printf("11-3=%d \n", a.func1(11, 3));
        System.out.printf("1-8=%d \n", a.func1(1, 8));

        System.out.println("------------------------");
        B b = new B();
        System.out.printf("11-3=%d \n", b.func1(11, 3));
        System.out.printf("1-8=%d \n", b.func1(1, 8));
        System.out.printf("11+3+9=%d \n", b.func2(11, 3));

    }

    static class A {
        public int func1(int num1, int num2) {
            return num1 - num2;
        }
    }

    static class B extends A {
        public int func1(int a, int b) {
            return a + b;
        }

        public int func2(int a, int b) {
            return func1(a, b) + 9;
        }
    }
}
