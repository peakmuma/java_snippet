package me.peak.algo;

import java.util.Arrays;
import java.util.Stack;

public class MaxSquare {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','1'},
                {'1','1','1','1','1'},
                {'0','1','1','1','1'},
                {'1','0','0','1','0'}};

        if (matrix == null || matrix.length == 0) {
            return;
        }
        int maxSquare = 0;
        int maxLength = 0;
        int[] height = new int[matrix[0].length];
        Arrays.fill(height, 0);
        int[][] length = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int width = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    width++;
                    height[j]++;
                    if (i == 0 || j == 0) {
                        length[i][j] = 1;
                    } else {
                        length[i][j] = Math.min(Math.min(length[i-1][j-1] + 1, width), height[j]);
                    }
                    maxLength = Math.max(length[i][j] , maxLength);
                } else {
                    width = 0;
                    height[j] = 0;
                    length[i][j] = 0;
                }
            }
        }
        maxSquare = maxLength * maxLength;
        System.out.println(maxSquare);
    }

    private static void stackSolution(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int maxSquare = 0;
        Stack<Integer> resStack = new Stack<>();
        int[] height = new int[matrix[0].length];
        Arrays.fill(height, 0);
        int[] leftBorderIndex = new int[matrix[0].length];
        int[] rightBorderIndex = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            calcLeftBorder(height, resStack, leftBorderIndex);
            calcRightBorder(height, resStack, rightBorderIndex);
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int length = Math.min(height[j], rightBorderIndex[j] - leftBorderIndex[j] - 1);
                    maxSquare = Math.max(length * length, maxSquare);
                }
            }
        }
        System.out.println(maxSquare);
    }

    private static void calcRightBorder(int[] height, Stack<Integer> resStack, int[] rightBorderIndex) {
        resStack.clear();
        for (int i = height.length - 1; i >= 0 ; i--) {
            while (!resStack.empty() && height[i] <= height[resStack.peek()]) {
                resStack.pop();
            }
            if (resStack.empty()) {
                rightBorderIndex[i] = height.length;
            } else {
                rightBorderIndex[i] = resStack.peek();
            }
            resStack.push(i);
        }

    }

    private static void calcLeftBorder(int[] height, Stack<Integer> resStack, int[] leftBorderIndex) {
        resStack.clear();
        for (int i = 0; i < height.length; i++) {
            while (!resStack.empty() && height[i] <= height[resStack.peek()]) {
                resStack.pop();
            }
            if (resStack.empty()) {
                leftBorderIndex[i] = -1;
            } else {
                leftBorderIndex[i] = resStack.peek();
            }
            resStack.push(i);
        }
    }
}
