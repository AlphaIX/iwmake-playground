package com.iwmake.datastructure.stack.rescursion;

/**
 * 迷宫问题
 * 递归
 * @author Dylan
 * @since 2020-10-28
 */
public class Maze {
    public static void main(String[] args) {
        // 创建一个二维数组模拟迷宫
        int[][] map = new int[8][7];
        // 使用1表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        // 输出地图
        System.out.println("地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 使用递归回溯给小球找路
        setWay(map, 1, 1);
        // 输出新的地图，小球走过并标识过的
        System.out.println("小球走过标识过的地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }


    // 小球起始点(1,1)
    // 小球终点为map(6,5),表示通路找到
    // 约定：map[i][j]=0表示该点未走过，1表示墙，2表示通路可以走，3表示该点已经走过，但是不通
    // 在走迷宫时需要一个策略：下->右->上->左,如果该点不通，再回溯

    /**
     * 使用递归给小球找路
     * @param map 表示地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果路通返回true，否则false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {// 如果当前点还没走过
                // 按照策略走 下->右->上->左
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map, i + 1, j)) { // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {// 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {// 上
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    // 说明该点不通，死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j] !=0, 可能是1,2,3
                return false;
            }
        }

    }
}
