package com.iwmake.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 思想，一开始默认第一个数有序
 * @author Dylan
 * @since 2020-10-29
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1,-1,89};
//        System.out.println("排序之前：" + Arrays.toString(arr));
//        //sortDetail(arr);
//        sortV(arr);

        // 测试80000个数 插入排序效率
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        //System.out.println(Arrays.toString(arr));
        long s1 = System.currentTimeMillis();
        sortV(arr);
        long e1 = System.currentTimeMillis();
        System.out.printf("执行时间为%dms \n", e1 - s1);

    }


    /**
     * 插入排序，
     * 默认第一个数有序，后面的数一次插入前面的有序序列中
     * @param arr
     */
    public static void sortV(int[] arr) {
        int inertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {

            // 定义待插入的数[可以将定义放到循环外]
            inertVal = arr[i];
            insertIndex = i - 1; //即arr[i]的前面这个数的下标

            // 给insertVal 找到插入位置！！！！
            // >=0保证不越界.
            // inertVal < arr[insertIndex]说明还没找到待插入位置, 需要将arr[insertIndex]后移
            while (insertIndex >= 0 && inertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex]; //后移
                insertIndex--;
            }
            // 当退出循环时，插入位置找到，即insertIndex+1位置
            if (insertIndex + 1 != i) { // 加不加判断都可以，试试加上对效率有没有提升【经测试没有明显提升】
                arr[insertIndex + 1] = inertVal;
            }
            //System.out.printf("第%d轮后：%s \n", i, Arrays.toString(arr));

        }

    }

    /**
     * 插入排序，推导过程 ,重点怎么移位的while部分
     * @param arr
     */
    public static void sortDetail(int[] arr) {
        // arr = {101,34,119,1}

        // 第一轮 {101,34,119,1}=>{34,101,119,1}
        // 定义待插入的数
        int inertVal = arr[1];
        int insertIndex = 1 - 1; //即arr[1]的前面这个数的下标

        // 给insertVal 找到插入位置！！！！
        // >=0保证不越界.
        // inertVal < arr[insertIndex]说明还没找到待插入位置, 需要将arr[insertIndex]后移
        while (insertIndex >= 0 && inertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //后移
            insertIndex--;
        }
        // 当退出循环时，插入位置找到，即insertIndex+1位置
        arr[insertIndex + 1] = inertVal;
        System.out.println("第一轮后：" + Arrays.toString(arr));


        // 第二轮 {34,101,119,1}=>{34,101,119,1}
        // 定义待插入的数
        inertVal = arr[2];
        insertIndex = 2 - 1; //即arr[2]的前面这个数的下标

        // 给insertVal 找到插入位置！！！！
        // >=0保证不越界.
        // inertVal < arr[insertIndex]说明还没找到待插入位置, 需要将arr[insertIndex]后移
        while (insertIndex >= 0 && inertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //后移
            insertIndex--;
        }
        // 当退出循环时，插入位置找到，即insertIndex+1位置
        arr[insertIndex + 1] = inertVal;
        System.out.println("第二轮后：" + Arrays.toString(arr));


        // 第三轮 {34,101,119,1}=>{1,34,101,119}
        // 定义待插入的数
        inertVal = arr[3];
        insertIndex = 3 - 1; //即arr[3]的前面这个数的下标

        // 给insertVal 找到插入位置！！！！
        // >=0保证不越界.
        // inertVal < arr[insertIndex]说明还没找到待插入位置, 需要将arr[insertIndex]后移
        while (insertIndex >= 0 && inertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex]; //后移
            insertIndex--;
        }
        // 当退出循环时，插入位置找到，即insertIndex+1位置
        arr[insertIndex + 1] = inertVal;
        System.out.println("第三轮后：" + Arrays.toString(arr));

        // 每一轮都执行相同的操作，使用for循环处理，==>sortV

    }
}
