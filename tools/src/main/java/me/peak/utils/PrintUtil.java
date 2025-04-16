package me.peak.utils;

import java.util.Collection;

public class PrintUtil {
    public static void printIntArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void printIntList(Collection<Integer> nums) {
        for (int i : nums) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void printStrList(Collection<String> strs) {
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
