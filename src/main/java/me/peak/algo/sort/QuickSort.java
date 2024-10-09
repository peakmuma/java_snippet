package me.peak.algo.sort;

/**
 * 快速排序，参考 https://blog.csdn.net/Alian_1223/article/details/123036590
 */
public class QuickSort {

    public static void main(String[] args) {
        int nums[] = new int[]{3,7,8,21,3,5,1,6};
        qSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    private static void qSort(int[] nums) {
        qSort(nums, 0, nums.length - 1);
    }

    private static void qSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = partition(nums, start, end);
        qSort(nums, start, pivot - 1);
        qSort(nums, pivot + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int pivotValue = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= pivotValue) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= pivotValue) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = pivotValue;
        return start;
    }
}
