package com.iwmake.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序--> 插入排序的高级版
 * @author Dylan
 * @since 2020-10-29
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        //sortDetail(arr);
//        sortV1(arr);

        // 测试80000个数 希尔排序效率
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long s1 = System.currentTimeMillis();
        //sortV1(arr); 交换法
        sortV2(arr); // 移位法
        long e1 = System.currentTimeMillis();
        System.out.printf("执行时间为%dms \n", e1 - s1);

    }


    /**
     * 希尔排序方案2，插入有序序列时使用 【移位法】
     * @param arr
     */
    public static void sortV2(int[] arr) {
        int temp = 0;
        int count = 0;

        // gap为步长(增量),逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 当退出while循环，找到temp的插入位置
                    arr[j] = temp;
                }
            }
            //System.out.printf("第%d轮：%s \n", ++count, Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序方案1，插入有序序列时使用 【交换法】 效率较低
     * @param arr
     */
    public static void sortV1(int[] arr) {
        int temp = 0;
        int count = 0;

        // gap为步长(增量),逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 将数据分成gap组
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共gap组，步长gap)
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明需要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.printf("第%d轮：%s \n", ++count, Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序 推导
     * @param arr
     */
    public static void sortDetail(int[] arr) {
        int temp = 0;

        // 第1轮，将10个数据分成5组  10/2=5
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素(共5组，每组2个元素，步长5)
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一轮：" + Arrays.toString(arr));


        // 第2轮，将10个数据分成2组 5/2=2
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素(共2组，每组5个元素，步长2)
            for (int j = i - 2; j >= 0; j -= 2) {
                // 如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二轮：" + Arrays.toString(arr));


        // 第3轮，将10个数据分成1组 2/2=1
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素(共1组，每组10个元素，步长1)
            for (int j = i - 1; j >= 0; j -= 1) {
                // 如果当前元素大于加上步长后的那个元素，说明需要交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三轮：" + Arrays.toString(arr));

        // 没一轮的操作类似，使用循环处理===>sortV

    }
}
