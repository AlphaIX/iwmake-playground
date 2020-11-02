package com.iwmake.algorithm.common;

/**
 * 二分查找非递归实现
 * @author Dylan
 * @since 2020-11-02
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 8);
        System.out.println("找到下标：" + index);
    }

    /**
     * @param arr    待查找的数组 ,arr升序排列
     * @param target 要查找的值
     * @return 返回下标 ，-1表示未找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;// 向左边查找
            } else {
                left = mid + 1; // 向右边查找
            }
        }
        return -1;
    }
}
