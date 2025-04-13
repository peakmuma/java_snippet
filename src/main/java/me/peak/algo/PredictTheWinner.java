package me.peak.algo;

//https://leetcode.cn/problems/predict-the-winner/description/
public class PredictTheWinner {

    public static void main(String[] args) {

    }

    public boolean predictTheWinner(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        return testWin(0, nums.length -1, nums, 0, 0, true);
    }

    static boolean testWin(int start, int end, int[] nums, int firstTotal, int secondTotal, boolean firstChose) {
        if (start == end - 1) {
            int max = 0;
            int min = 0;
            if (nums[start] > nums[end]) {
                max = nums[start];
                min = nums[end];
            } else {
                min = nums[start];
                max = nums[end];
            }
            if (firstChose) {
                return firstTotal + max >= secondTotal + min;
            } else {
                return firstTotal + min >= secondTotal + max;
            }
        }
        if (firstChose) {
            return testWin(start + 1, end, nums, firstTotal + nums[start], secondTotal, !firstChose)
                    || testWin(start, end - 1, nums, firstTotal + nums[end], secondTotal, !firstChose);
        } else {
            return testWin(start + 1, end, nums, firstTotal, secondTotal + nums[start], !firstChose)
                    && testWin(start, end - 1, nums, firstTotal, secondTotal + nums[end], !firstChose);
        }
    }

}
