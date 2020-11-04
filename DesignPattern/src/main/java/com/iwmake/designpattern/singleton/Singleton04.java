package com.iwmake.designpattern.singleton;

/**
 * 懒汉式->同步方法
 * @author Dylan
 * @since 2020-11-04
 */
public class Singleton04 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

    }

    /**
     * 懒汉式->同步方法
     * 缺点：效率太低，每次getInstance都要进入同步
     */
    static class Singleton {
        private static Singleton instance;

        // 1 构造器私有化
        private Singleton() {
        }

        // 2 提供一个公有静态方法，当第一次调用该方法时，才实例化对象
        // 方法同步
        public static synchronized Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
}
