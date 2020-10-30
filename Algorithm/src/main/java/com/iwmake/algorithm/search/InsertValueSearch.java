package com.iwmake.algorithm.search;

import java.util.Arrays;

/**
 * 插值查找. 前提：数据集有序
 * 数据分布均匀的情况下较快，如果元素之间间隔很大，不一定比二分查找快
 * @author Dylan
 * @since 2020-10-30
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println("待查找的数组为：" + Arrays.toString(arr));
        int index = insertValueSearch(arr, 0, arr.length - 1, 88);
        System.out.println("找到索引：" + index);

    }

    /**
     * 插值查找
     * @param arr     数组
     * @param left    左索引
     * @param right   右边索引
     * @param findVal 待查找的值
     * @return 返回第一个配配的索引
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("hekl");//看看方法查找了几次
        // 注意必须判断待查找的值是否在序列中，否则mid值可能越界，findVal参与了求mid值
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid 关键！！！
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) { // 向右边递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
