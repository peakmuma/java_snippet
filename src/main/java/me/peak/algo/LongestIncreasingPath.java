package me.peak.algo;

import java.util.Arrays;

// https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
public class LongestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix) {
        int[][] path = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(path[i], -1);
        }

        int maxPath = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int currentPath = findMaxPath(matrix, path, i, j);
                maxPath = Math.max(maxPath, currentPath);
            }
        }
        return maxPath;
    }

    int findMaxPath(int[][] matrix, int[][] path, int row, int column) {
        if (path[row][column] != -1) {
            return path[row][column];
        }
        int maxPath = 1;
        if (row > 0 && matrix[row][column] < matrix[row-1][column]) {
            int currentPath =  1 + findMaxPath(matrix, path, row -1 , column);
            maxPath = Math.max(maxPath, currentPath);
        }
        if (column > 0 && matrix[row][column] < matrix[row][column - 1]) {
            int currentPath =  1 + findMaxPath(matrix, path, row, column - 1);
            maxPath = Math.max(maxPath, currentPath);
        }
        if (row < matrix.length - 1 && matrix[row][column] < matrix[row + 1][column]) {
            int currentPath =  1 + findMaxPath(matrix, path, row + 1, column);
            maxPath = Math.max(maxPath, currentPath);
        }
        if (column < matrix[0].length - 1 && matrix[row][column] < matrix[row][column + 1]) {
            int currentPath =  1 + findMaxPath(matrix, path, row, column + 1);
            maxPath = Math.max(maxPath, currentPath);
        }
        path[row][column] = maxPath;
        return maxPath;
    }
}
