package com.iwmake.designpattern.singleton;

/**
 * 双重检查加锁
 * 推荐方式，既保证线程安全，又能实现懒加载
 * @author Dylan
 * @since 2020-11-04
 */
public class Singleton06 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

    }

    /**
     * 双重检查加锁
     * 推荐方式，既保证线程安全，又能实现懒加载
     */
    static class Singleton {
        /**
         * 注意：这里volatile关键字的作用是，禁止指令重排序，
         * 多线程环境下如果指令重排序，有可能会出现线程安全问题
         */
        private static volatile Singleton instance;

        // 1 构造器私有化
        private Singleton() {
        }

        // 2 提供一个公有静态方法，当第一次调用该方法时，才实例化对象
        public static Singleton getInstance() {
            // 第一次检查
            if (instance == null) {
                synchronized (Singleton.class) {
                    // 第二次检查
                    if(instance == null){
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
