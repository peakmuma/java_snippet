package me.peak.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuSolve {

    public static void main(String[] args) {
        int[][] nums = new int[][] {
                {0,4,6,9,0,3,0,0,0},
                {0,0,3,0,5,0,0,6,0},
                {9,0,0,0,0,2,0,0,3},
                {0,0,5,0,0,6,0,0,0},
                {8,0,0,0,0,0,0,1,0},
                {0,1,0,7,8,0,2,0,0},
                {0,0,0,0,0,0,0,5,0},
                {0,8,1,3,0,0,0,0,7},
                {0,0,0,8,0,0,1,0,4},
        };
        canSolve(nums);
    }

    static void canSolve(int[][] nums) {
        List<int[]> emptyCell = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (nums[i][j] == 0) {
                    emptyCell.add(new int[]{i, j});
                }
            }
        }
        if (canSolve(nums, emptyCell, 0)) {
            int index = 0;
            for (int i = 0; i < 9 && index < emptyCell.size(); i++) {
                for (int j = 0; j < 9 && index < emptyCell.size(); j++) {
                    int[] currentIndex = emptyCell.get(index);
                    if (i == currentIndex[0] && j == currentIndex[1]) {
                        System.out.print(nums[i][j]);
                        index++;
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("no solve");
        }
    }

    static boolean canSolve(int[][] nums, List<int[]> emptyCell, int index) {
        if (index == emptyCell.size()) {
            return true;
        }

        int[] currentIndex = emptyCell.get(index);

        List<Integer> canUseNumber = getCanUseNumber(nums, currentIndex[0], currentIndex[1]);

        if (canUseNumber.isEmpty()) {
            return false;
        }

        for (Integer num : canUseNumber) {
            nums[currentIndex[0]][currentIndex[1]] = num;
            if (canSolve(nums, emptyCell, index + 1)) {
                return true;
            }
        }
        nums[currentIndex[0]][currentIndex[1]] = 0;
        return false;
    }

    static List<Integer> getCanUseNumber(int[][] nums, int row, int column) {
        Set<Integer> existNum = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            existNum.add(nums[row][i]);
            existNum.add(nums[i][column]);
        }
        int rowStart = row / 3 * 3;
        int columnStart = column / 3 * 3;

        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = columnStart; j < columnStart + 3; j++) {
                existNum.add(nums[i][j]);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (!existNum.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }

    boolean rule1(int[][] nums, int target, int i, int j){
        return true;
    }
    boolean rule2(int[][] nums, int target, int i, int j){
        return true;
    }
    boolean rule3(int[][] nums, int target, int i, int j){
        return true;
    }
}
