package com.iwmake.designpattern.singleton;

/**
 * 使用枚举 创建单例
 * 推荐
 * @author Dylan
 * @since 2020-11-04
 */
public class Singleton08 {
    public static void main(String[] args) {
        // 测试
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance==instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());

        instance.sayOK();

    }

    enum Singleton{
        INSTANCE;
        public void sayOK(){
            System.out.println("ok");
        }
    }
}
