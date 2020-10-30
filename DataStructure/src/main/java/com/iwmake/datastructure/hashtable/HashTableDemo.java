package com.iwmake.datastructure.hashtable;

import java.util.Scanner;

/**
 * 哈希表
 * @author Dylan
 * @since 2020-10-30
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add 添加雇员");
            System.out.println("list 显示雇员");
            System.out.println("e 退出");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.find(id);
                    break;
                case "e":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }

    // 哈希表管理多条链表
    static class HashTable {
        private EmpLinkedList[] empLinkedListArray;
        private int size;

        public HashTable(int size) {
            this.size = size;
            empLinkedListArray = new EmpLinkedList[size];
            // ！！！！不要忘了分别初始化每一条链表
            for (int i = 0; i < size; i++) {
                empLinkedListArray[i] = new EmpLinkedList();
            }
        }

        //添加雇员
        public void add(Emp emp) {
            // 根据员工id计算应该添加到哪条链表，散列函数hash处理
            int bucket = hash(emp.id);
            // 添加
            empLinkedListArray[bucket].add(emp);

        }

        public Emp find(int id) {
            int bucket = hash(id);
            return empLinkedListArray[bucket].findById(id);
        }


        public int hash(int id) {
            return id % size;// 简单取模哈希
        }

        // 遍历哈希表
        public void list() {
            for (int i = 0; i < size; i++) {
                empLinkedListArray[i].list(i);
            }
        }
    }

    // 表示一个雇员
    static class Emp {
        public int id;
        public String name;
        public Emp next;

        public Emp(int id, String name) {
            super();
            this.id = id;
            this.name = name;
        }
    }

    // 链表
    static class EmpLinkedList {
        // 头指针指向第一个雇员
        private Emp head;// 默认null

        // 添加雇员
        // 假定添加雇员时，id是自增长的，因此将新雇员直接加入到本链表最后即可
        public void add(Emp emp) {
            // 第一次添加
            if (head == null) {
                head = emp;
                return;
            }
            Emp current = head;
            while (true) {
                if (current.next == null) { // 已到最后
                    break;
                }
                current = current.next;// 后移
            }
            current.next = emp;
        }

        // 根据id查找雇员
        public Emp findById(int id) {
            if (head == null) {
                System.out.println("链表为空");
                return null;
            }
            Emp current = head;
            while (true) {
                if (current.id == id) {
                    System.out.printf("已找到id%d雇员name=%s\n", current.id, current.name);
                    return current;
                }
                if (current.next == null) {
                    System.out.println("未找到指定id雇员");
                    return null;
                }
                current = current.next;
            }
        }

        // 遍历链表
        public void list(int num) {
            if (head == null) {
                System.out.printf("第%d链表为空\n", num);
                return;
            }
            Emp current = head;
            System.out.printf("第%d条链表信息为：", num);
            while (true) {
                System.out.printf("=> id=%d name=%s \t", current.id, current.name);
                if (current.next == null) {
                    break;
                }
                current = current.next;
            }
            System.out.println();
        }
    }
}
