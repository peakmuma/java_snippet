package me.peak.algo;

public class TargetSum {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        int S = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S > sum || S < -sum) {
            return;
        }
        if (S == sum || S == -sum) {
            int res = 1;
            for (int num : nums) {
                if (num == 0) {
                    res = res * 2;
                }
            }
            return;
        }
        int[][] positiveWayCount = new int[nums.length][sum+1];
        int[][] negativeWayCount = new int[nums.length][sum+1];
        int prevSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                positiveWayCount[i][nums[i]] = 1;
                negativeWayCount[i][nums[i]] = 1;
            } else {
                prevSum += nums[i-1];
                for (int j = 0; j <= prevSum; j++) {
                    if (positiveWayCount[i-1][j] > 0) {
                        positiveWayCount[i][j + nums[i]] += positiveWayCount[i-1][j];
                        if (j > nums[i]) {
                            positiveWayCount[i][j - nums[i]] += positiveWayCount[i-1][j];
                        } else {
                            negativeWayCount[i][nums[i] - j] += positiveWayCount[i-1][j];
                        }
                    }
                    if (negativeWayCount[i-1][j] > 0) {
                        negativeWayCount[i][j + nums[i]] += negativeWayCount[i-1][j];
                        if (j >= nums[i]) {
                            negativeWayCount[i][j - nums[i]] += negativeWayCount[i-1][j];
                        } else {
                            positiveWayCount[i][nums[i] - j] += negativeWayCount[i-1][j];
                        }
                    }
                }
            }
        }
        if (S > 0) {
            System.out.println(positiveWayCount[nums.length - 1][S]);
        } else {
            System.out.println(negativeWayCount[nums.length - 1][S]);
        }



    }
}
