package com.iwmake.designpattern.singleton;

/**
 * 使用静态内部类 创建单例
 * 推荐
 * @author Dylan
 * @since 2020-11-04
 */
public class Singleton07 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

    }

    /**
     * 使用静态内部类 创建单例
     * 推荐
     */
    static class Singleton {

        // 构造器私有化
        private Singleton() {
        }

        /**
         * JVM在装载类的时候是线程安全的
         */
        // 写一个静态内部类，该类中有一个静态属性Singleton
        private static class SingletonInstance {
            private static final Singleton INSTANCE = new Singleton();
        }

        // 提供一个公有静态方法，返回单例对象
        public static Singleton getInstance() {
            return SingletonInstance.INSTANCE;
        }
    }
}
