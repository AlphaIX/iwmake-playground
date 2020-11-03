package com.iwmake.algorithm.common;

import java.util.Arrays;

/**
 * 普里姆算法
 * @author Dylan
 * @since 2020-11-03
 */
public class PrimAlgorithm {
    public static void main(String[] args) {

        // 测试图是否创建成功
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        // 邻接矩阵的关系使用二维数组表示,10000较大的数表示不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        // 创建Graph
        Graph graph = new Graph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertex, data, weight);

        // 输出
        minTree.showGraph(graph);

        // 测试prim算法
        minTree.prim(graph, 0);

    }

    /**
     * 创建最小生成树-->村庄地图
     */
    static class MinTree {
        // 创建图的邻接矩阵
        public void createGraph(Graph graph, int vertex, char[] data, int[][] weight) {
            int i, j;
            for (i = 0; i < vertex; i++) {
                graph.data[i] = data[i];
                for (j = 0; j < vertex; j++) {
                    graph.weight[i][j] = weight[i][j];
                }
            }
        }

        // 显示图的邻接矩阵
        public void showGraph(Graph graph) {
            for (int[] link : graph.weight) {
                System.out.println(Arrays.toString(link));
            }
        }


        /**
         * prim算法
         * @param graph 图
         * @param v     表示从第几个顶点开始生成 'A'->0 'B'->1...
         */
        public void prim(Graph graph, int v) {
            int[] visited = new int[graph.vertex];// 标记顶点是否被访问过

            // 把当前节点标记为已经访问
            visited[v] = 1;

            // h1 h2记录两个顶点的下标
            int h1 = -1;
            int h2 = -1;
            int minWeight = 10000;// 临时变量
            for (int k = 1; k < graph.vertex; k++) {//因为有vertex个顶点，prim算法结束后，有vertex-1条边，k从1开始

                // 两次for循环每次结束即找到 最小权值
                for (int i = 0; i < graph.vertex; i++) {
                    for (int j = 0; j < graph.vertex; j++) {
                        if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                            minWeight = graph.weight[i][j];
                            h1 = i;
                            h2 = j;

                        }
                    }
                }

                // 找到一条边最小
                System.out.printf("边<%c,%c> 权值：%d \n", graph.data[h1], graph.data[h2], minWeight);
                // 标记访问
                visited[h2] = 1;
                // minWeight重置
                minWeight = 10000;

            }
        }
    }

    static class Graph {
        int vertex; // 节点个数
        char[] data;// 节点数据
        int[][] weight;// 边， 邻接矩阵

        public Graph(int vertex) {
            this.vertex = vertex;
            data = new char[vertex];
            weight = new int[vertex][vertex];
        }
    }
}
