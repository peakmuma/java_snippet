package me.peak.algo;

/*
    https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class MaxProductSubArray {
    public static void main(String[] args) {
        int[] nums = {-1,-2,4,-3};
        int maxProduct = nums[0];
        int curMaxProduct = nums[0];
        int curMinProduct = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int p1 = curMaxProduct * nums[i];
            int p2 = curMinProduct * nums[i];
            curMaxProduct = Math.max(nums[i], Math.max(p1, p2));
            curMinProduct = Math.min(nums[i], Math.min(p1, p2));
            maxProduct = Math.max(curMaxProduct, maxProduct);
        }
        System.out.println(maxProduct);
    }
}
