package me.peak.algo;

/**
 * https://leetcode-cn.com/problems/maximal-rectangle/
  这个思路真的是... 写完还是有点懵, 得好好把思路写下来了....
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','1'},
                {'1','1','1','1','1'},
                {'0','1','1','1','1'},
                {'1','0','0','1','0'}};
//        char[][] matrix = {{'0','1'}, {'1','1'}};
//        if (matrix == null || matrix.length == 0) {
//            return;
//        }
        int rectangle, maxRectangle = 0;
        int[][] dp = new int[matrix[0].length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    for (int k = 0; k <= j; k++) {
                        dp[j][k] = 0;
                    }
                } else {
                    dp[j][j]++;
                    rectangle = dp[j][j];
                    maxRectangle = Math.max(rectangle, maxRectangle);
                    for (int k = 0; k < j; k++) {
                        dp[j][k] = Math.min(dp[j-1][k], dp[j][j]);
                        rectangle = dp[j][k] * (j - k + 1);
                        maxRectangle = Math.max(rectangle, maxRectangle);
                    }
                }
            }
        }
        System.out.println(maxRectangle);
    }

}
