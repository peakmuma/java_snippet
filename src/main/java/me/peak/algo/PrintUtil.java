package me.peak.algo;

import java.util.List;

public class PrintUtil {
    public static void printIntArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void printIntList(List<Integer> nums) {
        for (int i : nums) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }
}
