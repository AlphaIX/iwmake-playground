package com.iwmake.algorithm.search;


import java.util.Arrays;

/**
 * 斐波那契查找算法
 * @author Dylan
 * @since 2020-10-30
 */
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};

        System.out.println(fibonacciSearch(arr,1234));

    }


    /**
     * 斐波那契查找
     * 使用非递归的方式
     * @param a 数组
     * @param key 要查找的值
     * @return 下标 不存在返回-1
     */
    public static int fibonacciSearch(int[] a, int key){
        int low = 0;
        int high = a.length -1;
        int k = 0; // 斐波那契分割值的下标
        int mid = 0;
        int[] f = fibonacci();// 斐波那契数列
        // 获取k
        while (high > f[k]-1){
            k++;
        }
        //因为f[k]值可能大于a的长度，因此需要使用Arrays类构建一个新的数组，并指向a
        int[] temp = Arrays.copyOf(a, k); // 默认不足的部分使用0填充,需要改为a[high]
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        // 开始查找key
        while (low<=high){
            mid = low + f[k-1] -1;
            if(key <temp[mid]){// 从数组左边找
                high = mid -1;
                // 为什么k--
                // 全部元素=前面的元素+后面的元素
                // f[k] = f[k-1] + f[k-2]
                // 因为前面有f[k-1]个元素，所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
                // 即在f[k-1]前面继续查找，--
                // 即下次循环mid = low + f[k-1-1] -1
                k--;
            }else if(key>temp[mid]){// 右边查找
                low = mid+1;
                // 为什么-2
                // 全部元素=前面的元素+后面的元素
                // f[k] = f[k-1] + f[k-2]
                // 因为后面有f[k-2]个元素，可以继续拆分f[k-1] = f[k-3]+f[k-4]
                // 下次循环mid = low + f[k-1-2] -1
                k -= 2;
            }else { //找到
                // 需要确定返回的是哪个下标
                if(mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        //
        return -1;
    }

    /**
     * 生成一个Fibonacci数列
     * @return
     */
    public static int[] fibonacci(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1] +f[i-2];
        }
        return f;
    }
}
