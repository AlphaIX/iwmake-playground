package com.iwmake.datastructure.stack.rescursion;

/**
 * 8皇后问题
 * @author Dylan
 * @since 2020-10-28
 */
public class Queen8 {
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d摆法 \n", count);
        System.out.printf("Judge判断了%d次 \n", countJudge);
    }


    int max = 8;// 总共有8个皇后
    // 定义一个数组arr，保存皇后放置位置结果，如，一种摆法为[0,4,7,5,2,6,1,3]
    /**
     * 理论上应该创建一个二维数组来表示棋盘，但实际上可以通过算法，用一个一维数组即可解决问题
     * arr[8] = [0,4,7,5,2,6,1,3] 中
     * 对应arr下标 表示第几行即第几个皇后
     * arr[i]=val 表示 第i+1个皇后，放在第i+1行的val+1列
     */
    int[] arr = new int[max];

    static int count = 0;//记录有多少种摆法
    static int countJudge = 0;// 记录判断了多少次

    /**
     * 放置第n个皇后
     * 特别注意：check每次(层)进入到check中都有一次 for (int i = 0; i < max; i++)循环, 因此会有回溯
     * @param n
     */
    private void check(int n) {
        if (n == max) { // n=8,其实8个皇后已经放好了，下标0开始的
            print();
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后 放到改行的第1列
            arr[n] = i;
            // 判断冲突
            if (judge(n)) {//不冲突
                // 接着放n+1个
                check(n + 1);
            }
            // 如果冲突，继续执行arr[n] = i，即将第n个皇后，放入本行的后一个位置，i++
        }
    }


    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放好的皇后冲突
     * @param n 第n个皇后
     * @return
     */
    private boolean judge(int n) {
        countJudge++;
        for (int i = 0; i < n; i++) {
            // arr[i] == arr[n] 表示判断第n个皇后 是否和前面n-1个皇后在同一列
            // Math.abs(n-i) == Math.abs(arr[n]-arr[i]) 表示判断第n个皇后是否和第i个皇后 是否在同一斜线
            // 判断是否在同一行，没有必要，n每次都在递增
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;

    }

    // 将皇后摆法的位置输出
    private void print() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


}
