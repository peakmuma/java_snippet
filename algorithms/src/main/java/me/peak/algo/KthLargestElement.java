package me.peak.algo;

/*
https://leetcode-cn.com/problems/top-k-frequent-elements/
 */

public class KthLargestElement {

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        int lastNoneLeaf = (k-1)/2;
        for (int i = lastNoneLeaf; i>=0; i--) {
            adjustHeap(nums, i, k);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                nums[0] = nums[i];
                adjustHeap(nums, 0, k);
            }
        }
        System.out.println(nums[0]);

    }

    static void adjustHeap(int[] heap, int index, int length) {
        int lChild = index * 2 + 1;
        int rChild = lChild + 1;
        if (lChild >= length) {
            return;
        }
        int minChild = lChild;
        if (rChild < length && heap[lChild] > heap[rChild]) {
            minChild = rChild;
        }
        if (heap[index] > heap[minChild]) {
            int temp = heap[index];
            heap[index] = heap[minChild];
            heap[minChild] = temp;
            adjustHeap(heap, minChild, length);
        }
    }



}
