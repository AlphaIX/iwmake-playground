package com.iwmake.datastructure.linkedlist;

/**
 * 约瑟夫环
 * @author Dylan
 * @since 2020-10-28
 */
public class CircleLinkedList {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        int nums = 5;
        list.addBoy(nums);
        //list.list();
        // 测试小孩出圈 2>4>1>5>3
        list.countBoy(1, 2, nums);
    }

}

class CircleSingleLinkedList {
    // first
    private Boy first = new Boy(-1);

    public void addBoy(int numbers) {
        if (numbers < 1) {
            System.out.println("number不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针
        for (int i = 1; i <= numbers; i++) {
            Boy boy = new Boy(i);

            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void list() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        // first 不能动
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first) { // 遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


    /**
     * 根据用户输入，计算小孩出圈顺序
     * @param startNo  表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums     表示最初由几个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        Boy helper = first; // 赋值指针帮助小孩出圈
        // 1.helper应事先指向最后节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 2.报数前，先让first和helper 移动 startNo-1次(即：j从1开始，而不是0)
        for (int j = 1; j < startNo; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 3.小孩报数时，first和helper  移动countNum-1次(即：j从1开始，而不是0)，然后出圈
        while (true) {
            if (helper == first) {// 圈中只剩下最后一个人
                break;
            }
            for (int j = 1; j < countNum; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时first指向的小孩，就是要出圈的小孩
            System.out.printf("小孩%d出圈\n", first.getNo());
            // 好好琢磨下面2语句
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中小孩编号%d \n", first.getNo());
    }
}

class Boy {
    private int no;//编号
    private Boy next;//指向下一个boy

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
