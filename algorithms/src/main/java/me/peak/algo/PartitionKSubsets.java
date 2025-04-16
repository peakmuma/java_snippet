package me.peak.algo;


import java.util.Arrays;

// https://leetcode.cn/problems/partition-to-k-equal-sum-subsets
public class PartitionKSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target  = sum / k;
        int[] bucket = new int[k];
        Arrays.sort(nums);
        return canPartition(nums, nums.length - 1, bucket, target);
    }

    /*
    此解法的核心思路是回溯遍历所有的可能性，回溯遍历的核心是 https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/solutions/1441006/by-lfool-d9o7/
     */
    public boolean canPartition(int[] nums, int startIndex, int[] bucket, int target) {
        if (startIndex == -1) {
            for (int num : bucket) {
                if (num != target) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (i > 0 && bucket[i] == bucket[i - 1]) {
                continue;
            }
            if (bucket[i] + nums[startIndex] > target) {
                continue;
            }
            bucket[i] += nums[startIndex];
            if (canPartition(nums, startIndex-1, bucket, target)) {
                return true;
            } else {
                bucket[i] -= nums[startIndex];
            }
        }

        return false;
    }
}
