package me.peak.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LargestNumber {

    public static void main(String[] args) {
//        int nums[] = new int[]{3,7,8,21,34,5,32,1,6};
//        int nums[] = new int[]{3,34,32};
        int nums[] = new int[]{43243,432};
        System.out.println(largestNumber(nums));
    }

    public static String largestNumber(int[] nums) {
        List<String> strs = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            strs.add(String.valueOf(nums[i]));
        }
        strs.sort((str1, str2) -> compare(str1, str2, 0,0));
        if (strs.get(0).startsWith("0")) {
            return "0";
        }
        return String.join("", strs);
    }

    static int compare(String str1, String str2, int str1Start, int str2Start) {
        int i = str1Start, j = str2Start;
        while (i < str1.length() && j < str2.length()) {
            int diff = str1.charAt(i) - str2.charAt(j);
            if (diff != 0) {
                return -diff;
            }
            i++;
            j++;
        }
        if (i == str1.length() && j == str2.length()) {
            return 0;
        }
        if (i >= str1.length()) {
            return compare(str1, str2, 0, j);
        } else {
            return compare(str1, str2, i, 0);
        }
    }


}
