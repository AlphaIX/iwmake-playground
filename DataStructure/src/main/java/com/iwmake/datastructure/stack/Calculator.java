package com.iwmake.datastructure.stack;

/**
 * 使用栈实现表达式计算器【中缀】,暂不支持括号
 * @author Dylan
 * @since 2020-10-28
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "70+20*6-4";
        // 创建2个栈，一个数字栈，一个符号栈
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack opStack = new ArrayStack(10);

        // 定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int op = 0;
        int res = 0;
        char ch = ' ';//将expression中扫描到的char保存

        String keepNum = "";//version 2 拼接多位数

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch
            if (opStack.isOp(ch)) {
                if (opStack.isEmpty()) {
                    // 为空直接入栈
                    opStack.push(ch);
                } else {
                    if (opStack.priority(ch) <= opStack.priority(opStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        op = opStack.pop();
                        res = numStack.calc(num1, num2, op);
                        // 计算结果入数栈
                        numStack.push(res);
                        // 当前操作符入符号栈
                        opStack.push(ch);
                    } else {
                        opStack.push(ch);
                    }
                }
            } else {
                // 数字 直接入数栈 0->ascii 48  1->ascii 49...
                //numStack.push(ch - 48); version 1只能处理个位数，不能处理多位数

                // version 2
                keepNum += ch;
                if (index == expression.length() - 1) {
                    // 避免下标越界，最后一个直接入栈
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 注意这里是查看当前index后一位是否是操作符，并没有移动index, 注意下标越界问题
                    if (opStack.isOp(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        // 重要！！！！清空当前keepNum
                        keepNum = "";
                    }
                }

            }
            // index+1
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 表达式扫描完毕，开始从2个栈中取数据运算
        while (true) {
            // 符号栈空，计算结束，数栈中只有一个值，即结果
            if (opStack.isEmpty()) {
                break;
            }

            num1 = numStack.pop();
            num2 = numStack.pop();
            op = opStack.pop();
            res = numStack.calc(num1, num2, op);
            // 计算结果入数栈
            numStack.push(res);
        }

        System.out.printf("表达式%s = %d ", expression, numStack.pop());


    }

    // 定义一个ArrayStack表示栈
    static class ArrayStack {
        private int maxSize;// 栈大小
        private int[] stack;// 栈，数据放在数组中
        private int top = -1;//栈顶，初始化为-1

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        // 入栈
        public void push(int value) {
            if (isFull()) {
                System.out.println("栈已满！");
                return;
            }
            top++;
            stack[top] = value;
        }

        // 出栈
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈已空");
            }
            int val = stack[top];
            top--;
            return val;
        }

        // 查看栈顶元素，不弹出栈
        public int peek() {
            return stack[top];
        }

        // 显示栈的情况[遍历栈]，从栈顶开始显示数据
        public void list() {
            if (isEmpty()) {
                System.out.println("栈空");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d \n", i, stack[i]);
            }
        }

        // 返回运算符的优先级，注意，优先级需预先确定，使用数字表示，数字越大，优先级越高
        // 目前先不考虑括号问题
        public int priority(int op) {
            if (op == '*' || op == '/') {
                return 1;
            } else if (op == '+' || op == '-') {
                return 0;
            } else {
                return -1;
            }
        }

        // 判断是不是一个运算符
        public boolean isOp(char var) {
            return var == '+' || var == '-' || var == '*' || var == '/';
        }

        // 计算方法
        public int calc(int num1, int num2, int op) {
            int res = 0;//计算结果
            switch (op) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num2 - num1;//注意顺序
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num2 / num1;
                default:
                    break;
            }
            return res;
        }
    }
}


