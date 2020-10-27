package com.iwmake.datastructure.queue;

import java.util.Scanner;

/**
 * 数组模拟队列
 * 使用环形队列，解决版本1问题
 * @author Dylan
 * @since 2020-10-27
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(4); // 设置为4，有效数据为3，有效数据=maxSize-1
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("s(show)显示队列");
            System.out.println("e(exit)退出程序");
            System.out.println("a(add)数据添加到队列");
            System.out.println("g(get)从队列取出数据");
            System.out.println("p(peek)查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.print("请输入一个数:");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g':
                    try {
                        int res = queue.get();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int res = queue.peek();
                        System.out.printf("队列头数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleQueue {
    private int maxSize; //队列大小
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 用数组模拟队列，存数据

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0; // 指向队列头，默认0
        rear = 0; // 指向队列尾部后面的一个位置，初始为0
    }

    // 判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列是否空
    public boolean isEmpty() {
        return front == rear;
    }

    // 入队
    public void add(int n) {
        if (isFull()) {
            System.out.println("队列已满！！！");
            return;
        }
        //rear++ version 1
        arr[rear] = n;
        // 将rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    // 出队列
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！不能取数据");
        }
        //front++; version 1
        //return arr[front]; version1
        //v2
        //1.先将front对应的值保存到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空 没有数据");
            return;
        }
        /** version 1
         for (int i = 0; i < arr.length; i++) {
         System.out.printf("arr[%d]=%d\n", i, arr[i]);
         }*/
        // v2 从front遍历，遍历多少个元素
        // 求出当前队列有效数据个数
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 显示队列的头数据，注意，不是取数据
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！没有数据");
        }
        //return arr[front + 1]; version 1
        return arr[front];
    }

    // 求出当前队列有效数据个数
    public int size() {
        // rear=1 front=0, maxSize=3 size=1
        return (rear + maxSize - front) % maxSize;// 好好想想为什么这样写
    }

}
