package com.example.suanfa.array;

/**
 * @author: luozijian
 * @date: 2022/3/8
 * @description:
 */
public class SetZero {

    public static void main(String[] args) {
        int[][] matrix = {{0, 2}, {4, 5}, {7, 8}};
        setZeroes(matrix);
    }

    public static void setZeroes(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

}