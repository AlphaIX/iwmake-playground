package com.iwmake.designpattern.factory.simple.v2.order;

import com.iwmake.designpattern.factory.simple.v2.factory.SimpleFactory;
import com.iwmake.designpattern.factory.simple.v2.pizza.Pizza;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 订购披萨
 * @author Dylan
 * @since 2020-11-05
 */
public class OrderPizza {

    //构造器
//    public OrderPizza() {
//        Pizza pizza = null;
//        String orderType;
//        do {
//            orderType = getType();
//            if (orderType.equalsIgnoreCase("greek")) {
//                pizza = new GreekPizza();
//                pizza.setName("希腊披萨");
//            } else if (orderType.equalsIgnoreCase("cheese")) {
//                pizza = new CheesePizza();
//                pizza.setName("奶酪披萨");
//            }
//            // 新增一个胡椒披萨，需要修改核心逻辑
//            else if(orderType.equalsIgnoreCase("pepper")){
//                pizza = new PepperPizza();
//                pizza.setName("胡椒披萨");
//            }
//            else {
//                break;
//            }
//            // 输出披萨制作过程
//            pizza.prepare();
//            pizza.bake();
//            pizza.cut();
//            pizza.box();
//        } while (true);
//    }

    // 构造器
    public OrderPizza(SimpleFactory factory) {
        setFactory(factory);
    }

    // 定义一个简单工厂对象
    SimpleFactory factory;
    Pizza pizza = null;

    public void setFactory(SimpleFactory factory) {
        String orderType="";//用户输入的
        this.factory = factory;

        do{
            orderType = getType();
            pizza = this.factory.createPizza(orderType);
            // 输出pizza
            if(pizza != null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else {
                System.out.println("订购披萨失败");
                break;
            }
        }while (true);
    }

    private String getType() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza type:");
            String str = strin.readLine();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
