package com.iwmake.datastructure.linkedlist;

/**
 * 简单单链表，每次新节点添加到末尾
 * @author Dylan
 * @since 2020-10-27
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        // 测试
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        LinkedList linkedList = new LinkedList();

//        linkedList.add(node1);
//        linkedList.add(node4);
//        linkedList.add(node3);
//        linkedList.add(node2);

        linkedList.addByOrder(node1);
        linkedList.addByOrder(node4);
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node2);

        linkedList.list();

        // 测试修改节点
        System.out.println("测试修改节点");
        HeroNode heroNode = new HeroNode(2, "卢俊义", "小卢~~~");
        linkedList.update(heroNode);
        linkedList.list();

        // 测试删除节点
        System.out.println("测试删除节点");
        linkedList.delete(1);
        linkedList.delete(4);
        linkedList.delete(2);
        linkedList.list();

    }
}

// 定义一个单链表管理英雄
class LinkedList {
    // 初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点，不考虑英雄编号问题
     * @param node
     */
    public void add(HeroNode node) {
        // 找到尾节点，将其next域指向 待添加的node
        // 因为head不能动，故需要一个复制指针
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //
            temp = temp.next;
        }
        // 当退出while循环时找到尾节点
        temp.next = node;
    }

    /**
     * 按指定顺序添加
     * @param node
     */
    public void addByOrder(HeroNode node) {
        // 头指针不能动
        HeroNode temp = head;
        boolean flag = false;// 待添加的英雄编号是否存在，默认false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > node.no) { //找到插入位置
                break;
            } else if (temp.next.no == node.no) {
                flag = true;//编号存在
                break;
            }
            temp = temp.next;//后移
        }

        if (flag) {
            System.out.printf("准备插入的英雄编号%d已存在，不能重复添加\n", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 根据节点编号来修改
     * @param node
     */
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;//是否找到节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = node.name;
            temp.nickname = node.nickname;
        } else {
            System.out.printf("没有找到要修改的节点id%d\n", node.no);
        }
    }

    /**
     * 删除指定编号节点
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false; //是否找到
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点不存在%d\n", no);
        }
    }

    /**
     * 显示链表【遍历】
     */
    public void list() {
        // 判空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 头节点不能动
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            // 将next后移 ，指针移动
            temp = temp.next;
        }

    }
}

// 定义英雄节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}