package me.peak.algo;

public class Search2dMatrix {
    public static void main(String[] args) {
        int[][] matrix ={
                {4,5,10,15,19,20,20},
                {4,9,12,15,22,23,26},
                {7,11,12,20,25,27,27},
                {10,14,17,23,27,30,32},
                {11,18,19,24,28,34,39}};

//                {1,   4,  7, 11, 15},
//                {2,   5,  8, 12, 19},
//                {3,   6,  9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}};
        for (int i = 0; i < 40; i++) {
            boolean res = search(matrix, i, 0 , 0, matrix.length - 1, matrix[0].length - 1);
            System.out.println(i + " " + res);
        }

//        boolean res = search(matrix, 25, 0 , 0, matrix.length - 1, matrix[0].length - 1);
//        System.out.println(res);
    }

    static boolean search(int[][] matrix, int target, int startI, int startJ, int endI, int endJ) {
        if (startI > endI || startJ > endJ || startI < 0 || startJ < 0 ||
                endI >= matrix.length || endJ >= matrix[0].length) {
            return false;
        }
        int i = startI + (endI - startI) / 2, j = startJ + (endJ - startJ) / 2;
        if (matrix[i][j] == target) {
            return true;
        } else if (matrix[i][j] < target) {
            boolean res1 = search(matrix, target, i + 1, startJ, endI, endJ);
            boolean res2 = search(matrix, target, startI, j + 1, i, endJ);
            return res1 || res2 ;
        } else {
            boolean res1 = search(matrix, target, startI, startJ, i - 1, endJ);
            boolean res2 = search(matrix, target, i, startJ, endI, j - 1);
            return res1 || res2 ;
        }
    }
}
