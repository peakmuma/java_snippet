package me.peak.algo;

/*
    https://leetcode-cn.com/problems/number-of-islands/
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid  = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        short num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    markColor(grid, i, j);
                }
            }
        }
        System.out.println(num);
    }

    private static void markColor(char[][] grid, int i, int j) {
        if (i < 0 || j< 0 ||i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        markColor(grid, i-1, j);
        markColor(grid, i+1, j);
        markColor(grid, i, j-1);
        markColor(grid, i, j+1);
    }

}
