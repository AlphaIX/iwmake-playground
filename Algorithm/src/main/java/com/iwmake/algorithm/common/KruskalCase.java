package com.iwmake.algorithm.common;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 * @author Dylan
 * @since 2020-11-03
 */
public class KruskalCase {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskal = new KruskalCase(vertex, matrix);
        kruskal.print();

        kruskal.kruskal();


    }

    private int edges;// 边个数
    private char[] vertex; // 顶点
    private int[][] matrix;// 邻接矩阵
    private static final int INF = Integer.MAX_VALUE; // 表示两个顶点不连通

    public KruskalCase(char[] vertex, int[][] matrix) {
        // 初始化顶点和边的个数
        int vLen = vertex.length;
        this.vertex = new char[vLen];
        // 初始化顶点 深拷贝
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }

        // 初始化边 深拷贝
        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edges++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0;// 表示最后结果数组的索引
        int[] ends = new int[edges];//用于保存 已有 最小生成树 中的每个顶点 在最小生成树中的终点
        // 创建结果数组,保存最后的最小生成树
        EdgeData[] rets = new EdgeData[edges];

        // 获取图中所有边的集合 12
        EdgeData[] edgeData = getEdges();
        System.out.printf("边的集合：一共%d条，%s \n", edgeData.length, Arrays.toString(edgeData));

        // 排序
        sortEdges(edgeData);
        // 遍历，将边添加到最小生成树，判断准备加入的边是否形成了回路
        for (int i = 0; i < edges; i++) {
            // 获取第i条边的第一个顶点
            int p1 = getPosition(edgeData[i].start);
            int p2 = getPosition(edgeData[i].end);

            // 获取p1在已有最小生成树的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            // 是否构成回路
            if (m != n) {// 没有
                ends[m] = n; // 设置m在已有最小生成树 中的 终点
                rets[index++] = edgeData[i]; // 有一条边加入最小生成树

            }
        }

        // 统计并打印最小生成树rets
        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }


    }

    // 打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%11d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序处理 冒泡
     * @param edges 边集合
     */
    private void sortEdges(EdgeData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {// 交换
                    EdgeData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 根据顶点值 返回对应的下标
     * @param ch 顶点值 如'A' 'B'...
     * @return 下标 或 -1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放入边数组中，后面需要遍历该数组
     * 通过邻接矩阵matrix 获取
     * @return [['A','B',12],['B','F',7].....]
     */
    private EdgeData[] getEdges() {
        int index = 0;
        EdgeData[] edgeData = new EdgeData[edges];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != INF) {
                    edgeData[index] = new EdgeData(vertex[i], vertex[j], matrix[i][j]);
                    index++;
                }
            }
        }
        return edgeData;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends ends是动态变化的，是在遍历过程中逐步形成的 故：不同时序下，同一个顶点i，返回的终点下标可能不同
     * @param i    传入的顶点下标
     * @return 返回订单i对应的 终点下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }


    class EdgeData {
        char start;// 边起点
        char end;// 边的另外一个点
        int weight;// 边 权值

        public EdgeData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeData[" +
                    "<" + start +
                    "," + end +
                    ">=" + weight +
                    ']';
        }
    }
}
