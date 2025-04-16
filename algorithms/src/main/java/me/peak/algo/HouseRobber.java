package me.peak.algo;

/**
 * https://leetcode-cn.com/problems/house-robber/
 */
public class HouseRobber {
    public static void main(String[] args) {

    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] maxCash = new int[nums.length];
        maxCash[0] = nums[0];
        maxCash[1] = nums[1];
        int beforeMaxCash = maxCash[0];
        for (int i = 2; i < nums.length; i++) {
            maxCash[i] = beforeMaxCash + nums[i];
            beforeMaxCash = Math.max(beforeMaxCash, maxCash[i-1]);
        }
        return Math.max(beforeMaxCash, maxCash[nums.length - 1]);
    }
}
