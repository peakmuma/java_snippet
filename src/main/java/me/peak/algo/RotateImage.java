package me.peak.algo;

/**
 * 题目链接: https://leetcode-cn.com/problems/rotate-image/
 */
public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        init(matrix);
        print(matrix);
        int temp;
        int n = matrix.length;
        for (int i = 0; i <= n/2 ; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
        print(matrix);
    }

    static void init(int[][] matrix) {
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = k++;
            }
        }
    }

    static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
