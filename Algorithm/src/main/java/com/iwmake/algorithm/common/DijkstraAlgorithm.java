package com.iwmake.algorithm.common;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 * 最短路径问题
 * @author Dylan
 * @since 2020-11-03
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可连通
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        // 创建Graph对象
        Graph graph = new Graph(vertex, matrix);
        // 显示图
        graph.showGraph();

        // 测试dijkstra算法
        graph.dijkstra(2);

        // 显示结果
        graph.showDijkstra();

    }


    /**
     * 已访问顶点集合
     */
    static class VisitedVertex {
        // 记录各个顶点是否访问过，1是，0否，会动态更新
        int[] alreadyArr;
        // 每个下标对应的值为前一个顶点下标，会动态更新
        int[] preVisited;
        // 记录出发顶点到其他顶点的距离，会动态更新，求的最短距离就会存放到dis
        int[] dis;

        /**
         * 构造器
         * @param length 表示顶点个数
         * @param index  表示出发点下标
         */
        public VisitedVertex(int length, int index) {
            this.alreadyArr = new int[length];
            this.preVisited = new int[length];
            this.dis = new int[length];
            // 初始化dis
            Arrays.fill(dis, 65535);
            this.alreadyArr[index] = 1; // 设置出发点已访问
            this.dis[index] = 0;// 设置出发点访问距离为0
        }

        /**
         * 判断index顶点是否访问过
         * @param index
         * @return
         */
        public boolean in(int index) {
            return alreadyArr[index] == 1;
        }

        /**
         * 更新出发顶点到index顶点的距离
         * @param index
         * @param len
         */
        public void updateDis(int index, int len) {
            dis[index] = len;
        }

        /**
         * 更新pre这个顶点的前驱为index顶点
         * @param pre
         * @param index
         */
        public void updatePre(int pre, int index) {
            preVisited[pre] = index;
        }

        /**
         * 返回出发顶点到index顶点的距离
         * @param index
         */
        public int getDis(int index) {
            return dis[index];
        }

        /**
         * 继续选择并返回访问顶点，比如这里的G完后，就是A作为新的访问顶点（注意不是出发顶点）
         * @return
         */
        public int updateArr() {
            int min = 65535, index = 0;
            for (int i = 0; i < alreadyArr.length; i++) {
                if (alreadyArr[i] == 0 && dis[i] < min) {
                    min = dis[i];
                    index = i;
                }
            }
            alreadyArr[index] = 1; // 更新index被访问
            return index;
        }

        /**
         * 显示最后的结果，即将三个数组的情况输出
         */
        public void show() {
            System.out.println("====================");
            for (int i : alreadyArr) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i : preVisited) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i : dis) {
                System.out.print(i + " ");
            }
            //为了方便显示最短距离
            System.out.println();
            char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
            int count = 0;
            for (int i : dis) {
                if (i != 65535) {
                    System.out.print(vertex[count] + "(" + i + ")");
                } else {
                    System.out.println("N ");
                }
                count++;
            }
        }
    }

    /**
     * 图
     */
    static class Graph {
        private char[] vertex;
        private int[][] matrix;
        VisitedVertex vv;

        /**
         * 构造器
         * @param vertex
         * @param matrix
         */
        public Graph(char[] vertex, int[][] matrix) {
            this.vertex = vertex;
            this.matrix = matrix;
        }

        /**
         * 显示图
         */
        public void showGraph() {
            for (int[] link : matrix) {
                System.out.println(Arrays.toString(link));
            }
        }

        /**
         * 显示结果
         */
        public void showDijkstra() {
            vv.show();
        }

        /**
         * Dijkstra算法实现
         * @param index 表示出发点对应下标
         */
        public void dijkstra(int index) {
            vv = new VisitedVertex(vertex.length, index);
            update(index);
            for (int j = 0; j < vertex.length; j++) {
                index = vv.updateArr(); // 选择并返回新的访问顶点
                update(index);
            }
        }

        /**
         * 更新index下标顶点到周围顶点的距离，和周围顶点的前驱顶点
         * @param index
         */
        private void update(int index) {
            int len = 0;
            //
            for (int j = 0; j < matrix[index].length; j++) {
                // 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离和
                len = vv.getDis(index) + matrix[index][j];
                // 如果顶点没有被访问过，并且len小于顶点到j顶点的距离，就需要更新
                if (!vv.in(j) && len < vv.getDis(j)) {
                    vv.updatePre(j, index);// 更新j顶点的前驱为index顶点
                    vv.updateDis(j, len);// 更新出发点到j的距离
                }
            }
        }
    }
}
