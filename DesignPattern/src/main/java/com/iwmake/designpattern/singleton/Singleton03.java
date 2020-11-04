package com.iwmake.designpattern.singleton;

/**
 * 懒汉式->普通（线程不安全）
 * @author Dylan
 * @since 2020-11-04
 */
public class Singleton03 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

    }

    /**
     * 懒汉式->普通（线程不安全）
     */
    static class Singleton {
        private static Singleton instance;

        // 1 构造器私有化
        private Singleton() {
        }

        // 2 提供一个公有静态方法，当第一次调用该方法时，才实例化对象
        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
}
