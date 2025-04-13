package me.peak.algo;


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/burst-balloons/
 */
public class BurstBalloons {

    public static void main(String[] args) {
        int[] nums = new int[]{1,5};
        System.out.println(maxCoins(nums));
    }

    static int maxCoins(int[] nums) {
        Map<String, Integer> map = new HashMap<>();
        return maxCoins(nums, nums.length, map);
    }

    static int maxCoins(int[] nums, int count, Map<String, Integer> map) {
        if (count == 0) {
            return 0;
        }
        int maxRes = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                continue;
            }
            int currentNum = nums[i];
            int product = calcProduct(nums, i);
            nums[i] = -1;
            String key = getKey(nums);
            if (!map.containsKey(key)) {
                map.put(key, maxCoins(nums, count - 1, map));
            }
            int tempRes = product + map.get(key);
            maxRes = Math.max(maxRes, tempRes);
            nums[i] = currentNum;
        }
        return maxRes;
    }

    static String getKey(int[] nums) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                if (s.length() > 0) {
                    s.append(',');
                }
                s.append(i);
            }
        }
        return s.toString();
    }

    static int calcProduct(int[] nums, int index) {
        int res = nums[index];
        int i = index - 1;
        while (i >=0 && nums[i] == -1) {
            i--;
        }
        if (i >=0 ) {
            res *= nums[i];
        }
        i = index + 1;
        while (i < nums.length && nums[i] == -1) {
            i++;
        }
        if (i < nums.length) {
            res *= nums[i];
        }
        return res;
    }
}
