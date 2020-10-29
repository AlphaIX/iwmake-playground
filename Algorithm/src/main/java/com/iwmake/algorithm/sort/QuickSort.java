package com.iwmake.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author Dylan
 * @since 2020-10-29
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println("排序后：" + Arrays.toString(arr));

        // 测试80000个数 快速排序效率
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long s1 = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long e1 = System.currentTimeMillis();
        System.out.printf("执行时间为%dms \n", e1 - s1);

    }


    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标
        // pivot中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量，交换时使用
        // while循环让比pivot小的值放到左边，比pivot大的放到右边
        while (l < r) {

            // 在pivot的左边一直找，找到大于等于pivot值才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找，找到小于等于pivot值才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // l>=r 说明pivot 的左右两边值，已经按照， 左边都小于pivot，右边都大于pivot
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 交换完毕后，发现arr[l]==pivot，前移r--
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 交换完毕后，发现arr[r]==pivot，后移l++
            if (arr[r] == pivot) {
                l += 1;
            }

        }
        // 如果l==r,必须l++,r--否则栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
