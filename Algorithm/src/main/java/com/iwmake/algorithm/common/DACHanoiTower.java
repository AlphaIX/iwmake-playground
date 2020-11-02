package com.iwmake.algorithm.common;

/**
 * 分治算法Divide and Compute
 * 汉诺塔案例
 * @author Dylan
 * @since 2020-11-02
 */
public class DACHanoiTower {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    /**
     *
     * @param num 一共几个盘
     * @param a A塔
     * @param b B塔
     * @param c C塔
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        // 只有一个盘
        if (num == 1) {
            System.out.printf("第%d个盘从%c->%c \n", num, a, c);
        } else {
            // n>=2总是看成2个盘
            // 1、先把上面的所有盘A->B,移动过程中会使用到c
            hanoiTower(num - 1, a, c, b);
            // 2、把最下面的盘A->C
            System.out.printf("第%d个盘从%c->%c \n", num, a, c);
            // 3、把B所有盘B->C,移动过程借助a
            hanoiTower(num - 1, b, a, c);
        }
    }
}
