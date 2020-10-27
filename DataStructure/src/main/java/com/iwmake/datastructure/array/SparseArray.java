package com.iwmake.datastructure.array;

/**
 * 稀疏数组
 * @author Dylan
 * @since 2020-10-27
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个二维数组11*11,棋盘0表示没有棋子，1黑子，2白子

        int[][] chessArr = new int[11][11];

        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;

        System.out.println("原始二维数组：");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("将二维数组转稀疏数组：");
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum=" + sum);
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = chessArr.length; // 记录原始数组的行数
        sparseArr[0][1] = chessArr[0].length; // 记录原始数组的列数
        sparseArr[0][2] = sum; // 记录原始数组，有效值个数

        // 遍历原始二维数组，将有效值存入稀疏数组
        int count = 0;//用以记录是第几个非0数
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        System.out.println("得到的稀疏数组为:");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        System.out.println();
        System.out.println("将稀疏数组恢复成原始的二维数组：");
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
