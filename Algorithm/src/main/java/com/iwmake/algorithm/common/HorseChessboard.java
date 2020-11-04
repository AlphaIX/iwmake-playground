package com.iwmake.algorithm.common;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘【骑士周游】 问题
 * @author Dylan
 * @since 2020-11-04
 */
public class HorseChessboard {
    public static void main(String[] args) {
        System.out.println("开始执行...");
        // 测试算法
        X = 8;
        Y = 8;
        int row = 1;//马儿初始位置行，从1开始编号
        int column = 1;// 马儿初始位置列，从1开始编号
        // 创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];// 初始值都是false

        long start = System.currentTimeMillis();
        travelChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "ms");

        // 输出棋盘
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    private static int X;// 棋盘的列数
    private static int Y;// 棋盘的行数
    private static boolean[] visited;// 标记位置是否访问过
    private static boolean finished;// 是否所有位置都访问过


    /**
     * 完成骑士周游算法
     * @param chessboard 棋盘
     * @param row        马儿当前位置行 从0开始
     * @param column     马儿当前位置列 从0开始
     * @param step       第几步，初始位置就是第1步
     */
    public static void travelChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;// 标记该位置已经访问
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> points = next(new Point(column, row));

        // 优化：非递减排序，减少回溯次数
        sort(points);// 注释本行，查看效率比较

        // 遍历points
        while (!points.isEmpty()) {
            Point p = points.remove(0);// 取出下一个可以走的位置
            // 判断是否访问过
            if (!visited[p.y * X + p.x]) {
                travelChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        // 判断马儿是否完成了任务，如果没有完成任务将棋盘置0
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;// 重新标记该位置为未访问
        } else {
            finished = true;
        }

    }

    /**
     * 根据当前位置point，计算马还能走哪些位置,最多有8个位置
     * @param current
     * @return
     */
    public static ArrayList<Point> next(Point current) {
        ArrayList<Point> points = new ArrayList<>();
        Point p1 = new Point();
        // 判断马是否可以走5这个位置
        if ((p1.x = current.x - 2) >= 0 && (p1.y = current.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        // 判断马是否可以走6这个位置
        if ((p1.x = current.x - 1) >= 0 && (p1.y = current.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        // 判断马是否可以走7这个位置
        if ((p1.x = current.x + 1) < X && (p1.y = current.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        // 判断马是否可以走0这个位置
        if ((p1.x = current.x + 2) < X && (p1.y = current.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        // 判断马是否可以走1这个位置
        if ((p1.x = current.x + 2) < X && (p1.y = current.y + 1) < Y) {
            points.add(new Point(p1));
        }
        // 判断马是否可以走2这个位置
        if ((p1.x = current.x + 1) < X && (p1.y = current.y + 2) < Y) {
            points.add(new Point(p1));
        }
        // 判断马是否可以走3这个位置
        if ((p1.x = current.x - 1) >= 0 && (p1.y = current.y + 2) < Y) {
            points.add(new Point(p1));
        }
        // 判断马是否可以走4这个位置
        if ((p1.x = current.x - 2) >= 0 && (p1.y = current.y + 1) < Y) {
            points.add(new Point(p1));
        }

        return points;

    }

    /**
     * 根据当前这一步，所有的下一步的可选位置数，进行非递减排序。减少回溯次数
     * @param points
     */
    public static void sort(ArrayList<Point> points) {
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取o1下一步可选位置个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
