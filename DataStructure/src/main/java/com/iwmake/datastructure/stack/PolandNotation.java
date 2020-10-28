package com.iwmake.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式【后缀表达式】 计算器实现，注意，暂只支持整数计算
 * @author Dylan
 * @since 2020-10-28
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // (30+4)*5-6 => 30 4 + 5 * 6 -
        // 为了方便，逆波兰表达式中数字和符号 用空格隔开
        //String suffixExpression = "30 4 + 5 * 6 -";

        // 将一个中缀表达式转成后缀表达式
        String expression = "1+((2+3)*4)-5";
        //String expression = "2+3*5";
        List<String> stringList = toInfixExpression(expression);
        System.out.println(stringList);
        List<String> parseList = parseSuffixExpression(stringList);
        System.out.println("后缀表达式为：" + parseList);


        //List<String> listString = getListString(suffixExpression);

        //System.out.println(listString);
        int res = calc(parseList);
        System.out.println("计算的结果是：" + res);

    }

    /**
     * 核心，中缀转后缀表达式
     * @param inList
     * @return
     */
    // 将中缀表达式list 转换成后缀表达式list
    public static List<String> parseSuffixExpression(List<String> inList) {
        // 定义2个栈
        Stack<String> s1 = new Stack<>();// 符号栈
        //Stack<String> s2 = new Stack<>();// s2在整个转换过程中没有pop操作，并且后续还需要逆序输出，不使用栈，直接使用list替代
        List<String> s2 = new ArrayList<>();// 用于存储中间结果

        // 遍历inList
        for (String item : inList) {
            // 如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号，则依次弹出s1栈顶运算符，并压入s2,直到遇到左括号位置，此时丢弃一对括号
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();// ！！！将左括号弹出 丢弃
            } else {// go===>
                // 处理运算符优先级问题
                // 当item的优先级<=s1栈顶运算符优先级：将s1栈顶pop加入s2,再次转到go===>
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将item入栈
                s1.push(item);
            }
        }

        // 将s1中剩余的加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        //按顺序输出就是对应的逆波兰表达式
        return s2;

    }

    // 将中缀表达式，转成对应的list，方便遍历
    public static List<String> toInfixExpression(String expresion) {
        List<String> ls = new ArrayList<>();
        int i = 0;// 一个指针，辅助遍历expression
        String str = "";// 对多位数拼接接收
        char c;// 当前字符
        do {
            // 如果c是一个非数字, add 到ls
            if ((c = expresion.charAt(i)) < 48 || (c = expresion.charAt(i)) > 57) {
                ls.add("" + c);
                i++;// i后移
            } else {// 考虑多位数
                while (i < expresion.length() && (c = expresion.charAt(i)) >= 48 && (c = expresion.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
                str = "";

            }
        } while (i < expresion.length());
        return ls;
    }

    // 将一个逆波兰表达式放入一个ArrayList中，方便遍历
    public static List<String> getListString(String expression) {
        String[] strings = expression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String str : strings) {
            list.add(str);
        }
        return list;
    }

    // 完成逆波兰表达式计算
    public static int calc(List<String> ls) {
        // 1.创建一个栈，只需一个即可
        Stack<String> stack = new Stack<>();
        //
        for (String item : ls) {
            if (item.matches("\\d+")) {// 匹配多位数
                // 2.如果是数字直接入栈
                stack.push(item);
            } else {
                // 3. 如果是符号，pop出两个数，计算结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;// 注意后弹出的减去先弹出的
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;// 注意后弹出的除以先弹出的
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        // 栈中留下的数字就是运算结果
        return Integer.parseInt(stack.pop());

    }

    // Operation类，返回一个运算符对应的优先级
    static class Operation {
        private static int ADD = 1;
        private static int SUB = 1;
        private static int MUL = 2;
        private static int DIV = 2;

        public static int getValue(String op) {
            int result = 0;
            switch (op) {
                case "+":
                    result = ADD;
                    break;
                case "-":
                    result = SUB;
                    break;
                case "*":
                    result = MUL;
                    break;
                case "/":
                    result = DIV;
                    break;
                default:
                    System.out.println("不存在该运算符");
                    break;
            }
            return result;
        }
    }
}
