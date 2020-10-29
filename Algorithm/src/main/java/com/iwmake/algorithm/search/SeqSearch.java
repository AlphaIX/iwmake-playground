package com.iwmake.algorithm.search;

/**
 * 线性查找
 * @author Dylan
 * @since 2020-10-29
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89}; //可以是没有顺序的数组
        int index = seqSearch(arr, 11);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("已找到，下标：" + index);
        }

    }

    /**
     * 返回第一个匹配的位置
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        // 线性查找-->逐一比对
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
