package com.iwmake.datastructure.linkedlist;

/**
 * @author Dylan
 * @since 2020-10-28
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        System.out.println("双向链表测试：");
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(4, "林冲", "豹子头");

        LinkedList2 doubleLinkedList = new LinkedList2();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);
        doubleLinkedList.list();

        //修改测试
        System.out.println();
        System.out.println("修改测试：");
        HeroNode2 newNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newNode);
        doubleLinkedList.list();

        // 删除测试
        System.out.println();
        System.out.println("删除测试：");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();

        // 测试按顺序添加
        HeroNode2 newNodeAdd = new HeroNode2(3, "吴用", "智多星");
        System.out.println();
        System.out.println("按顺序添加：");
        doubleLinkedList.addByOrder(newNodeAdd);
        doubleLinkedList.list();

    }
}


// 定义双向链表管理英雄
class LinkedList2 {
    // 初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加节点到双向链表最后，不考虑英雄编号问题
     * @param node
     */
    public void add(HeroNode2 node) {
        // 找到尾节点，将其next域指向 待添加的node
        // 因为head不能动，故需要一个复制指针
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            //
            temp = temp.next;
        }
        // 当退出while循环时找到尾节点
        temp.next = node;
        node.prev = temp; // prev指向temp 形成双向链表
    }

    /**
     * 按指定顺序添加
     * @param node
     */
    public void addByOrder(HeroNode2 node) {
        // 头指针不能动
        HeroNode2 temp = head;
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
            node.prev = temp;
            node.next.prev = node;
        }
    }

    /**
     * 根据节点编号来修改
     * @param node
     */
    public void update(HeroNode2 node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//是否找到节点
        while (true) {
            if (temp == null) {
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
     * 删除节点，自我删除
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false; //是否找到
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.prev.next = temp.next;

            // 需要判断是否是最后一个节点
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }

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
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            // 将next后移 ，指针移动
            temp = temp.next;
        }

    }

    /**
     * 获取链表节点个数，（如果带头节点，排除头节点）
     * @return
     */
    public int length(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int len = 0;
        HeroNode current = head.next;
        while (current != null) {
            len++;
            current = current.next;
        }
        return len;
    }
}


// 定义英雄节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点 默认为null
    public HeroNode2 prev; // 指向上一个节点，默认为null

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
