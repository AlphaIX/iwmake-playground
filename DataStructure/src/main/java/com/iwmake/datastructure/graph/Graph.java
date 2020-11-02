package com.iwmake.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图
 * @author Dylan
 * @since 2020-11-02
 */
public class Graph {
    public static void main(String[] args) {
        // 测试图创建是否ok
        //int n = 5;
        int n = 8;
        //String[] vertexLabels = {"A", "B", "C", "D", "E"};
        String[] vertexLabels = {"1", "2", "3", "4", "5", "6", "7", "8"};
        // 创建图对象
        Graph graph = new Graph(n);
        for (String val : vertexLabels) {
            graph.insertVertex(val);
        }
        // 添加边
        // A-B A-C B-C B-D B-E vertexLabels1
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        // vertexLabels2
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示邻接矩阵
        graph.showGraph();

        // 深度优先遍历测试
        System.out.println("深度遍历：");
        graph.dfs();
        System.out.println();

        // 广度优先遍历测试
        graph.isVisited = new boolean[n];
        System.out.println("广度遍历：");
        graph.bfs();

    }

    private List<String> vertexList;// 存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;// 表示边的数目
    private boolean[] isVisited;

    //构造器
    public Graph(int n) {
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 遍历所有节点 都进行广度搜索
     */
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    /**
     * 对一个节点进行广度优先遍历
     * @param isVisited
     * @param i
     */
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列头节点对应的下标
        int w;//表示邻接节点下标
        // 队列,记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();

        // 访问
        System.out.print(getVertex(i) + "=>");
        // 标记为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            // 取出队列头节点下标
            u = queue.removeFirst();
            // 得到第一个邻接节点下标
            w = getFirstNeighbor(u);
            while (w != -1) { // 找到
                if (!isVisited[w]) {
                    System.out.print(getVertex(w) + "=>");
                    // 标记已经访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以u为当前点，找w后面的下一个邻接点，，，这里体现广度优先
                w = getNextNeighbor(u, w);

            }
        }
    }

    /**
     * 对dfs进行重载，遍历所有节点，并进行dfs
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 对一个节点深度优先遍历
     * @param isVisited
     * @param i         第一次是0
     */
    private void dfs(boolean[] isVisited, int i) {
        // 访问
        System.out.print(getVertex(i) + "->");
        // 将当前节点设置为已经访问
        isVisited[i] = true;
        // 查找节点i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }

    }


    /**
     * 根据前一个节点的下标来获取下一个邻接节点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 得到第一个邻接节点下标
     * @param index
     * @return 如果存在返回对应的下标，否则-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }

        return -1;
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 返回v1和v2的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 返回节点i对应的数据 0-->A 1-->B...
     * @param i
     * @return
     */
    public String getVertex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回顶点个数
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的数目
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 插入顶点
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1     表示点的下标 如"A"-"B" "A"->0 "B"->1
     * @param v2     第二个点的下标
     * @param weight 表示是否关联
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}
