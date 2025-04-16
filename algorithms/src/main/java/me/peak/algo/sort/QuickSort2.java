package me.peak.algo.sort;

public class QuickSort2 {

    public static void main(String[] args) {
        int[] nums = new int[]{3,1,4,5,2,7,8,9,-1,-3,-2};
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        quickSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }


    static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = partition(nums, start, end);
        quickSort(nums, start, partition - 1);
        quickSort(nums, partition + 1, end);
    }


    static int partition(int[] nums, int start, int end) {

        if (start == end) {
            return start;
        }

        int low = start + 1;
        int high = end;

        int num = nums[start];
        while (low <= high) {
            while (low < nums.length && nums[low] <= num) {
                low++;
            }

            while (high >= 0 && nums[high] >= num) {
                high--;
            }

            if (low < high) {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
            }
        }

        nums[start] = nums[low - 1];
        nums[low - 1] = num;

        return low - 1;
    }
}
