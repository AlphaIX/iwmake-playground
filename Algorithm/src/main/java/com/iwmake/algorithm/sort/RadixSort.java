package com.iwmake.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序(桶排序),暂不支持负数
 * @author Dylan
 * @since 2020-10-29
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        //sortDetail(arr);
//        sort(arr);

        // 测试80000个数 基数排序效率
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long s1 = System.currentTimeMillis();
        sort(arr);
        //System.out.println("排序后：" + Arrays.toString(arr));
        long e1 = System.currentTimeMillis();
        System.out.printf("执行时间为%dms \n", e1 - s1);

    }

    public static void sort(int[] arr) {
        // 先得到数组中最大的数的位数
        int max = arr[0];// 假定第一个数最大
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 最大数位数为:
        int maxLength = (max + "").length();

        // 定义一个二维数组表示10个桶，每个桶就是一维数组
        // 说明：二维数组包含10个一维数组
        // 为了防止溢出，每个一维数组(桶)的大小，定义为arr.length
        // 空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个桶中实际存放的元素个数，
        // 定义一个一维数组来记录各个桶，每次放入的数据个数 bucketElements[0]表示第一个桶中有效数据个数...
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            // 每一轮 针对每个元素的对应的位进行排序处理，第一轮个位，第二轮十位，...
            for (int j = 0; j < arr.length; j++) {
                // 取出元素的个位数
                int digitOfElement = arr[j] / n % 10;
                // 放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            // 按照桶的顺序，依次取出数据放入原来的数组
            int index = 0;
            // 遍历每一个桶，
            for (int k = 0; k < bucket.length; k++) {
                // 如果桶中有数据才入原数组
                if (bucketElementCounts[k] != 0) {
                    // 取出数据
                    for (int m = 0; m < bucketElementCounts[k]; m++) {
                        arr[index] = bucket[k][m];
                        index++;
                    }
                }
                // 第i+1轮处理后，需要将 bucketElementCounts[k] = 0 ！！！！重要
                bucketElementCounts[k] = 0;
            }
            //System.out.printf("第%d轮：%s \n", i + 1, Arrays.toString(arr));

        }
    }

    /**
     * 基数排序推导分析
     * @param arr
     */
    public static void sortDetail(int[] arr) {

        // 定义一个二维数组表示10个桶，每个桶就是一维数组
        // 说明：二维数组包含10个一维数组
        // 为了防止溢出，每个一维数组(桶)的大小，定义为arr.length
        // 空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个桶中实际存放的元素个数，
        // 定义一个一维数组来记录各个桶，每次放入的数据个数 bucketElements[0]表示第一个桶中有效数据个数...
        int[] bucketElementCounts = new int[10];


        // 第一轮排序 针对每个元素的【个位数】进行排序处理
        for (int j = 0; j < arr.length; j++) {
            // 取出元素的个位数
            int digitOfElement = arr[j] / 1 % 10;
            // 放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        // 按照桶的顺序，依次取出数据放入原来的数组
        int index = 0;
        // 遍历每一个桶，
        for (int k = 0; k < bucket.length; k++) {
            // 如果桶中有数据才入原数组
            if (bucketElementCounts[k] != 0) {
                // 取出数据
                for (int m = 0; m < bucketElementCounts[k]; m++) {
                    arr[index] = bucket[k][m];
                    index++;
                }
            }
            // 第一轮处理后，需要将 bucketElementCounts[k] = 0 ！！！！重要
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮：" + Arrays.toString(arr));


        // 第二轮排序 针对每个元素的【十位数】进行排序处理
        for (int j = 0; j < arr.length; j++) {
            // 取出元素的个位数
            int digitOfElement = arr[j] / 10 % 10;
            // 放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        // 按照桶的顺序，依次取出数据放入原来的数组
        index = 0;
        // 遍历每一个桶，
        for (int k = 0; k < bucket.length; k++) {
            // 如果桶中有数据才入原数组
            if (bucketElementCounts[k] != 0) {
                // 取出数据
                for (int m = 0; m < bucketElementCounts[k]; m++) {
                    arr[index] = bucket[k][m];
                    index++;
                }
            }
            // 第二轮处理后，需要将 bucketElementCounts[k] = 0 ！！！！重要
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮：" + Arrays.toString(arr));


        // 第三轮排序 针对每个元素的【百位数】进行排序处理
        for (int j = 0; j < arr.length; j++) {
            // 取出元素的个位数
            int digitOfElement = arr[j] / 100 % 10;
            // 放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        // 按照桶的顺序，依次取出数据放入原来的数组
        index = 0;
        // 遍历每一个桶，
        for (int k = 0; k < bucket.length; k++) {
            // 如果桶中有数据才入原数组
            if (bucketElementCounts[k] != 0) {
                // 取出数据
                for (int m = 0; m < bucketElementCounts[k]; m++) {
                    arr[index] = bucket[k][m];
                    index++;
                }
            }
            // 第三轮处理后，需要将 bucketElementCounts[k] = 0 ！！！！重要
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮：" + Arrays.toString(arr));


    }
}
