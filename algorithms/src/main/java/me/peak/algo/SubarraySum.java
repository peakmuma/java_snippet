package me.peak.algo;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int k  = 3;
        int sum = 0, count = 0;
        Map<Integer, Integer> sumCountMap = new HashMap<>();
        sumCountMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += sumCountMap.getOrDefault(sum - k, 0);
            sumCountMap.put(sum, sumCountMap.getOrDefault(sum, 0) + 1);
        }
        System.out.println(count);
    }
}
