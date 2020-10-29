package com.iwmake.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author Dylan
 * @since 2020-10-29
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3, 9, -1, 10, 20}; // 待排序数组
        // 冒泡排序演变过程
        //sortDetail(arr);
        // 冒泡排序未优化
        //sortV1(arr);

        //sortV2(arr);
        //System.out.println("排序后的数组为："+ Arrays.toString(arr));

        // 测试80000个数排序效率 v1和v2
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*800000);
        }
        //System.out.println(Arrays.toString(arr));
        long s1 = System.currentTimeMillis();
        //sortV1(arr);
        sortV2(arr);
        long e1 = System.currentTimeMillis();
        System.out.printf("执行时间为%dms \n", e1-s1);


    }

    /**
     * 版本2已优化
     * 冒泡排序的优化：如果在某趟排序中，没有发生一次交换，可以提前结束冒泡排序
     * @param arr
     */
    public static void sortV2(int[] arr) {
        int temp = 0;
        // 冒泡排序的时间复杂度O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;//标识变量，是否发生交换
            // 内层循环次数arr.length-1-i
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {

                    flag = true;

                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.printf("第%d趟排序后的数组%s\n", i + 1, Arrays.toString(arr));
            if(!flag){ // 没有发生过交换
                break;
            }
        }
    }

    /**
     * 冒泡排序的时间复杂度O(n^2)
     * 冒泡排序的第一个版本，未优化
     * @param arr
     */
    public static void sortV1(int[] arr){
        int temp = 0;
        // 冒泡排序的时间复杂度O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            // 内层循环次数arr.length-1-i
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.printf("第%d趟排序后的数组%s\n", i + 1, Arrays.toString(arr));
        }
    }

    /**
     * 拆解冒泡排序的过程
     * @param arr
     */
    public static void sortDetail(int[] arr) {
        // 冒泡排序演变过程 需要arr.length-1趟排序
        // 第一趟排序，将最大的数排在最后
        int temp = 0; // 临时变量
        for (int j = 0; j < arr.length - 1; j++) {
            // 比较 交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        // 第一趟排序后的数组
        System.out.println("第一趟排序后的数组：" + Arrays.toString(arr));

        // 第二趟排序，将第二大的数排在倒数第二位length -1 -1
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            // 比较 交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第二趟排序后的数组：" + Arrays.toString(arr));

        // 第三趟排序，将第三大的数排在倒数第三位length -1 -2
        for (int j = 0; j < arr.length - 1 - 2; j++) {
            // 比较 交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第三趟排序后的数组：" + Arrays.toString(arr));

        // 第四趟排序，将第四大的数排在倒数第四位length -1 -3
        for (int j = 0; j < arr.length - 1 - 2; j++) {
            // 比较 交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第四趟排序后的数组：" + Arrays.toString(arr));
    }
}
