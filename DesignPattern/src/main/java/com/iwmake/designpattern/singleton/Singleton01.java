package com.iwmake.designpattern.singleton;

/**
 * 饿汉式->静态变量
 * @author Dylan
 * @since 2020-11-04
 */
public class Singleton01 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

    }

    /**
     * 饿汉式->静态变量
     * 优点：写法简单，在类装载的时候就完成了实例化，避免了线程同步问题
     * 缺点：在类装载时候实例化，如果从始至终未使用这个实例，造成内存浪费
     */
    static class Singleton {
        // 1 构造器私有化
        private Singleton() {
        }

        // 2 本类内部创建对象实例
        private static final Singleton instance = new Singleton();

        // 3 提供一个公有静态方法，返回实例对象
        public static Singleton getInstance() {
            return instance;
        }
    }
}
