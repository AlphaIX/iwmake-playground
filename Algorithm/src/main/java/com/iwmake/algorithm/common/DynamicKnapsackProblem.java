package com.iwmake.algorithm.common;

/**
 * 动态规划算法，背包问题
 * @author Dylan
 * @since 2020-11-02
 */
public class DynamicKnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};// 物品重量
        int[] val = {1500,3000,2000};//物品价值
        int m = 4;//背包容量
        int n = val.length;// 物品个数

        // 创建二维数组
        // v[i][j] 表示在前i个物品中能够装入背包容量为j的 背包中的最大价值
        int[][] v = new int[n+1][m+1];
        // 为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行，第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;// 第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//第一行设置为0
        }

        // 根据前面的公式 动态规划处理
        for (int i = 1; i < v.length ; i++) { // 不处理第一行
            for (int j = 1; j < v[0].length; j++) { // 不处理第一列
                // 公式
                if(w[i-1]>j){ //注意：i从1开始，故-1
                    v[i][j] = v[i-1][j];
                }else {
                    // i从1开始需要-1
                    //v[i][j] = Math.max(v[i-1][j], val[i-1] + v[i-1][j-w[i-1]]);
                    // 为了记录背包放入情况，不能简单使用上面的公式
                    if(v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }


        // 输出一下v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        
        // 遍历path
        System.out.println("===================");
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                if(path[i][j] ==1){
                    System.out.printf("第%d个商品放入背包\n",i);
                }
            }
        }

    }
}
