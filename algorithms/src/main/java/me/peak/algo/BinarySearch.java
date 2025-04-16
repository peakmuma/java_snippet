package me.peak.algo;

public class BinarySearch {
    /*
    nums 数组经过一个有序数组右移x位得到
     */
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,8,9,1,2,3};
        System.out.println(find(nums, 0, nums.length - 1, 9));
        System.out.println(find(nums, 0, nums.length - 1, 1));
        System.out.println(find(nums, 0, nums.length - 1, 8));
        System.out.println(find(nums, 0, nums.length - 1, 5));
    }

    public static int find (int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            int res = find(nums, start, mid - 1, target);
            if (res != -1) {
                return res;
            } else if (nums[end] < nums[mid]) {
                return find(nums,  mid + 1, end, target);
            } else {
                return -1;
            }
        } else {
            int res = find(nums, mid + 1, end , target);
            if (res != -1) {
                return res;
            } else if (nums[start] > nums[mid]) {
                return find(nums, start, mid - 1, target);
            } else {
                return -1;
            }
        }
    }
}
