package com.iwmake.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找：前提，数据集有序
 * @author Dylan
 * @since 2020-10-29
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};

        //int index = binarySearch(arr, 0, arr.length - 1, 88);
        ArrayList<Integer> index = binarySearchAll(arr, 0, arr.length - 1, 1000);
        System.out.println("找到index：" + index.toString());


    }

    /**
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的值
     * @return 找到返回下标，否则返回-1,只返回第一个匹配的下标
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 当left>right时，说明递归整个数组但是没有找到
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {// 向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 返回所有符合条件的下标
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static ArrayList<Integer> binarySearchAll(int[] arr, int left, int right, int findVal) {
        // 当left>right时，说明递归整个数组但是没有找到
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {// 向右递归
            return binarySearchAll(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearchAll(arr, left, mid - 1, findVal);
        } else {
            // 返回所有匹配项下标
            ArrayList<Integer> res = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                res.add(temp);
                temp -= 1;
            }

            res.add(mid);

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                res.add(temp);
                temp += 1;
            }

            return res;
        }
    }


}
