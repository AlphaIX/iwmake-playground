package com.iwmake.datastructure.queue;

import java.util.Scanner;

/**
 * 数组模拟队列
 * 该版本存在问题：数组不能重复使用，全部取出后，依然不能添加新元素
 * @author Dylan
 * @since 2020-10-27
 */
public class ArrayQueue {
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop){
            System.out.println("s(show)显示队列");
            System.out.println("e(exit)退出程序");
            System.out.println("a(add)数据添加到队列");
            System.out.println("g(get)从队列取出数据");
            System.out.println("p(peek)查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key){
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
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int res = queue.peek();
                        System.out.printf("队列头数据是%d\n", res);
                    }catch (Exception e){
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

class Queue {
    private int maxSize; //队列大小
    private int front; // 队列头,指向队列头的前一个位置
    private int rear; // 队列尾,指向队列尾部，即队列最后一个数据
    private int[] arr; // 用数组模拟队列，存数据

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头的前一个位置
        rear = -1; // 指向队列尾部，即队列最后一个数据
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
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
        rear++;
        arr[rear] = n;
    }

    // 出队列
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！不能取数据");
        }
        front++;
        return arr[front];
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空 没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列的头数据，注意，不是取数据
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空！没有数据");
        }
        return arr[front + 1];
    }

}
