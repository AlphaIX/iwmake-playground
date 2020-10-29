package com.iwmake.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author Dylan
 * @since 2020-10-29
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[arr.length];
//        System.out.println("排序前：" + Arrays.toString(arr));
//        mergeSort(arr, 0, arr.length - 1, temp);
//        System.out.println("排序后：" + Arrays.toString(arr));

        // 测试80000个数 归并排序效率
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        int[] temp = new int[arr.length];
        long s1 = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, temp);
        //System.out.println("排序后：" + Arrays.toString(arr));
        long e1 = System.currentTimeMillis();
        System.out.printf("执行时间为%dms \n", e1 - s1);


    }

    /**
     * 分+合方法
     * @param arr   待排序数组
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            // 向左递归分解
            mergeSort(arr, left, mid, temp);
            // 向右
            mergeSort(arr, mid + 1, right, temp);
            // 到合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并方法
     * @param arr   待排序数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;// 初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引
        int t = 0;// t指向temp数组的当前索引

        //1.
        // 先把左右两边(有序)的数据按照规则填充到temp数据
        // 直到左右两边有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            // 如果左边有序序列的当前元素小于等于，右边有序序列的当前元素
            // 即将左边的当前元素拷贝的temp数组，然后t后移，i后移
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //2.把有剩余数据的一边，剩下的数据依次填充到temp
        while (i <= mid) { //走完第一步还存在此条件
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //3.把temp数组元素拷贝到arr
        // 注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        // 第一次合并，tempLeft=0,right=1,
        // 第二次tempLeft=2,right=3,
        // 第三次tempLeft=0,right=3...
        //System.out.printf("tempLeft=%d,right=%d\n",tempLeft, right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
