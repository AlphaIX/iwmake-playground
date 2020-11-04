package com.iwmake.principle.dependencyinversion;

/**
 * @author Dylan
 * @since 2020-11-04
 */
public class DependencyInversion01 {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());

    }

    static class Email {
        public String getInfo() {
            return "电子邮件信息：hello world";
        }
    }

    /**
     * 完成Person接收消息功能
     * 方式一
     * //1.简单，比较容易想到
     * //2.如果我们，现在要获取微信，短信，等信息，则需要新增类，和增加相应的接收方法
     * //3.解决思路：引入一个抽象接口IReceiver,表示接收者，这样Person类与IReceiver发生依赖
     * @see {@link DependencyInversion02}
     */
    static class Person {
        public void receive(Email email) {
            System.out.println(email.getInfo());
        }
    }
}
