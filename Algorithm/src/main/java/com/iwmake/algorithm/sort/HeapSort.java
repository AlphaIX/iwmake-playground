package com.iwmake.algorithm.sort;

import java.util.Arrays;

/**
 * O(nlogn) 线性对数阶
 * 堆排序，需要二叉树的前置知识，如顺序存储二叉树数组表示方法
 * @author Dylan
 * @since 2020-10-31
 */
public class HeapSort {
    public static void main(String[] args) {
        // 升序排列，大顶推，如果降序小顶堆
//        int[] arr = {4,6,8,5,9,-99,34,2,-9};
//        heapSort(arr);

        // 测试80000个数 堆排序效率
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long s1 = System.currentTimeMillis();
        heapSort(arr);
        long e1 = System.currentTimeMillis();
        System.out.printf("执行时间为%dms \n", e1 - s1);

    }

    public static void heapSort(int[] arr){
        int temp=0;
        System.out.println("堆排序");

        // 分布完成
        // 第一次
//        adjustHeap(arr,1,arr.length);
//        System.out.println(Arrays.toString(arr));
//
//        // 第二次
//        adjustHeap(arr,0,arr.length);
//        System.out.println(Arrays.toString(arr));

        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //System.out.println(Arrays.toString(arr));
        for (int j = arr.length-1; j >0 ; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0, j);
        }
        //System.out.println(Arrays.toString(arr));

    }

    /**
     * 将一个数组（二叉树），调整成一个大顶堆
     * @param arr 待调整的数组（二叉树）
     * @param i 表示非叶子节点在数组中的位置，将i对应的节点调整为大顶堆
     * @param length 表示多少个元素调整，length逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];// 当前值保存在临时变量
        // 开始调整
        // k指向i节点的左子节点
        for (int k = i*2+1; k <length ; k=k*2+1) {
            if(k+1<length && arr[k] < arr[k+1]){
                k++;// k指向右子节点
            }
            if(arr[k] >temp){// 子节点大于父节点
                arr[i] = arr[k]; // 把较大的值赋值给当前节点
                i=k;// !!! 重要，i指向k，继续循环比较
            }else {
                break;
            }
        }
        // for循环结束后，已经将以i节点为父节点的树的最大值，放在了最顶（局部）
        arr[i] = temp;// 将temp放在调整后的位置
    }
}
