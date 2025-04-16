package me.peak.algo;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class NiXuDui {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2,3,1};
        System.out.println(reversePairs(nums));
    }
    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int[] temp = new int[nums.length];
        return sort(nums, 0, nums.length - 1, temp);
    }

    public static int sort(int[] nums, int start, int end, int[] temp) {
        if (end == start) {
            return 0;
        } else if (end - start == 1) {
            return merge(nums, start, end, end, temp);
        }
        int mid = start + (end - start) / 2;
        int res1 = sort(nums, start, mid - 1, temp);
        int res2 = sort(nums, mid, end, temp);
        int res3 = merge(nums, start, mid, end, temp);
        return res1 + res2 + res3;
    }

    public static int merge(int[] nums, int start1, int start2, int end, int[] temp) {
        if (end <= start1) {
            return 0;
        }
        int i = start1, j = start2, k = start1, res = 0;
        while (i < start2 && j <= end) {
            if (nums[i] <= nums[j]) {
                res += j - start2;
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i < start2) {
            temp[k++] = nums[i++];
            res += end - start2 + 1;
        }
        while (j <= end) {
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp, start1, nums, start1, end - start1 + 1);
        return res;
    }
}
