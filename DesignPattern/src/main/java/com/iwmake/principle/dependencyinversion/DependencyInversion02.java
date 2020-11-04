package com.iwmake.principle.dependencyinversion;

/**
 * @author Dylan
 * @since 2020-11-04
 */
public class DependencyInversion02 {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());

        person.receive(new WeiXin());

    }

    /**
     * 定义接口
     */
    interface IReceiver {
        String getInfo();
    }

    static class Email implements IReceiver {
        public String getInfo() {
            return "电子邮件信息：hello world";
        }
    }

    static class WeiXin implements IReceiver {
        public String getInfo() {
            return "微信消息：hello ok";
        }
    }

    /**
     * 完成Person接收消息功能
     * 方式二
     */
    static class Person {
        public void receive(IReceiver receiver) {
            System.out.println(receiver.getInfo());
        }
    }

    /**
     * 补充：依赖传递三种方式
     * 接口传递
     * 构造方法传递
     * setter方法传递
     */
}

