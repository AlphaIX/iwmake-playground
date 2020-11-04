package com.iwmake.algorithm.common;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 * 最短路径问题
 * @author Dylan
 * @since 2020-11-04
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        // 测试图
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        // 创建图对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        // 测试Floyd算法
        graph.floyd();
        // 查看结果
        graph.show();

    }

    /**
     * 图
     */
    static class Graph {
        private char[] vertex;// 顶点
        private int[][] dis;// 从各个顶点出发到其他顶点的距离
        private int[][] pre;// 到达目标顶点的前驱顶点

        /**
         * 构造器
         * @param length 大小
         * @param matrix 邻接矩阵
         * @param vertex 顶点数组
         */
        public Graph(int length, int[][] matrix, char[] vertex) {
            this.vertex = vertex;
            this.dis = matrix;
            this.pre = new int[length][length];
            // pre 存放的是前驱顶点的下标
            for (int i = 0; i < length; i++) {
                Arrays.fill(pre[i], i);
            }
        }

        // 显示pre和dis数组
        public void show() {
            for (int k = 0; k < dis.length; k++) {
                // pre
                for (int i = 0; i < dis.length; i++) {
                    System.out.print(vertex[pre[k][i]] + " ");
                }
                System.out.println();
                // dis
                for (int i = 0; i < dis.length; i++) {
                    System.out.printf("%c到%c最短路径是%d  ", vertex[k], vertex[i], dis[k][i]);
                }
                System.out.println();
                System.out.println();
            }
        }

        /**
         * 弗洛伊德算法
         */
        public void floyd() {
            int len = 0; // 距离
            // 对中间顶点遍历，k为中间顶点下标
            for (int k = 0; k < dis.length; k++) {
                // 从i顶点开始出发 {'A', 'B', 'C', 'D', 'E', 'F', 'G'}
                for (int i = 0; i < dis.length; i++) {
                    // j为到达顶点
                    for (int j = 0; j < dis.length; j++) {
                        len = dis[i][k] + dis[k][j];//==>从i出发经过k到达j
                        if (len < dis[i][j]) { // ==>直连
                            dis[i][j] = len;// 更新距离
                            pre[i][j] = pre[k][j]; // 更新前驱顶点
                        }
                    }
                }
            }
        }
    }
}
