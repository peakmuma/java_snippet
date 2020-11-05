package me.peak.algo;

import java.util.ArrayDeque;

public class ShortestUnsortedSubarray {
    public static void main(String[] args) {
        int[] nums = {2,3};
        if (nums == null || nums.length < 2) {
            return;
        }
        int[] leftBorder = new int[nums.length];
        int[] rightBorder = new int[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] > nums[i]) {
                stack.pollLast();
            }
            leftBorder[i] = stack.isEmpty()? -1 : stack.peekLast();
            stack.addLast(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i]) {
                stack.pollLast();
            }
            rightBorder[i] = stack.isEmpty() ? nums.length : stack.peekLast();
            stack.addLast(i);
        }
        int left = nums.length, right = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                left = Math.min(left, leftBorder[i]);
                right = Math.max(right, rightBorder[i-1]);
            }
        }
        System.out.println(right - left - 1);
    }
}
