package me.peak.algo;


/**
 * https://leetcode-cn.com/problems/burst-balloons/
 */
public class BurstBalloons {
    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] sortedIndex = new int[nums.length];
        for (int i = 0; i < sortedIndex.length; i++) {
            sortedIndex[i] = i;
        }
        sort(nums, sortedIndex, 0, nums.length - 1);

        int res = calcProduct(nums, sortedIndex, 0);

        System.out.println(res);

    }

    static int calcProduct(int[] nums, int[] sortedIndex, int index) {
        int diff = sortedIndex.length - index;
        int product, currentIndex, leftIndex, rightIndex;
        if (diff == 1) {
            currentIndex = sortedIndex[index];
            product = nums[currentIndex];
            return product;
        }
        if (diff == 2) {
            currentIndex = sortedIndex[index];
            product = nums[currentIndex];
            rightIndex = sortedIndex[index + 1];
            product *= nums[rightIndex];
            return product + calcProduct(nums, sortedIndex, index + 1);
        }
        if (diff == 3) {
            leftIndex = sortedIndex[index];
            currentIndex = sortedIndex[index + 1];
            rightIndex = sortedIndex[index + 2];
            product = nums[currentIndex] * nums[leftIndex];
            product *= nums[rightIndex];
            if ((currentIndex >= leftIndex && currentIndex <= rightIndex)
            || (currentIndex <= leftIndex && currentIndex >= rightIndex)) {
                sortedIndex[index + 1] = leftIndex;
            } else if ((rightIndex >= currentIndex && rightIndex <= leftIndex)
                || (rightIndex <= currentIndex && rightIndex >= leftIndex)) {
                sortedIndex[index + 2] = currentIndex;
                sortedIndex[index + 1] = leftIndex;
            }
            return product + calcProduct(nums, sortedIndex, index + 1);
        }

        currentIndex = sortedIndex[index];
        product = nums[currentIndex];

        leftIndex = currentIndex - 1;
        while (leftIndex >= 0 && nums[leftIndex] == -1) {
            leftIndex--;
        }
        if (leftIndex >= 0) {
            product *= nums[leftIndex];
        }

        rightIndex = currentIndex + 1;
        while (rightIndex < nums.length && nums[rightIndex] == -1) {
            rightIndex++;
        }
        if (rightIndex < nums.length) {
            product *= nums[rightIndex];
        }

        nums[currentIndex] = -1;
        return product + calcProduct(nums, sortedIndex, index + 1);
    }

    static void sort(int[] nums,int[] sortedIndex, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start + 1, j = end;
        while (true) {
            while (nums[sortedIndex[i]] < nums[sortedIndex[start]] && i < end) {
                i++;
            }
            while (nums[sortedIndex[j]] > nums[sortedIndex[start]] && j > start) {
                j--;
            }
            if (i < j) {
                swap(sortedIndex, i, j);
            } else {
                break;
            }
        }
        swap(sortedIndex, start, j);
        sort(nums,sortedIndex, start, j - 1);
        sort(nums,sortedIndex, j + 1, end);

    }

    static void swap(int[] sortedIndex, int a, int b) {
        int temp = sortedIndex[b];
        sortedIndex[b] = sortedIndex[a];
        sortedIndex[a] = temp;
    }
}
