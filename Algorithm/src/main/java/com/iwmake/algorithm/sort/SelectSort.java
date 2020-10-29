package com.iwmake.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author Dylan
 * @since 2020-10-29
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -8, 45, 23};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        //sortDetail(arr);
//        sortV1(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));

        // 测试80000个数 选择排序效率
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        //System.out.println(Arrays.toString(arr));
        long s1 = System.currentTimeMillis();
        sortV1(arr);
        long e1 = System.currentTimeMillis();
        System.out.printf("执行时间为%dms \n", e1 - s1);

    }

    /**
     * 双重for循环，时间复杂度O(n^2)
     * @param arr
     */
    public static void sortV1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 第i+1轮
            int minIndex = i;// 假定最小值的索引就是i，即第i+1个数
            int min = arr[i];
            // 和后一个比较,故j初始值+1
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { //说明假定值不是最小
                    min = arr[j];//重置min
                    minIndex = j;// 重置minIndex
                }
            }
            // 将最小值放在arr[i], 交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.printf("第%d轮：%s \n", i + 1, Arrays.toString(arr));
        }
    }

    /**
     * 逐步推导的方式，拆解选择排序
     * @param arr
     */
    public static void sortDetail(int[] arr) {

        // 第1轮
        int minIndex = 0;// 假定最小值的索引就是0，即第一个数
        int min = arr[0];
        // 和后一个比较故j初始值+1
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) { //说明假定值不是最小
                min = arr[j];//重置min
                minIndex = j;// 重置minIndex
            }
        }
        // 将最小值放在arr[0], 交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一轮：" + Arrays.toString(arr));

        // 第2轮
        minIndex = 1;// 假定最小值的索引就是1，即第二个数
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) { //说明假定值不是最小
                min = arr[j];//重置min
                minIndex = j;// 重置minIndex
            }
        }
        // 将最小值放在arr[1], 交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第二轮：" + Arrays.toString(arr));

        // 第3轮
        minIndex = 2;// 假定最小值的索引就是2，即第三个数
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            if (min > arr[j]) { //说明假定值不是最小
                min = arr[j];//重置min
                minIndex = j;// 重置minIndex
            }
        }
        // 将最小值放在arr[2], 交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三轮：" + Arrays.toString(arr));

        // 推导过程中可以看到，每一轮都执行 类似的操作，故可以使用一个循环解决==>sortV1

    }
}
