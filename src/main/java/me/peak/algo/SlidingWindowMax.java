package me.peak.algo;

import java.util.ArrayDeque;

public class SlidingWindowMax {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7,3,8,4,1};
        int k = 5;
        if (nums.length == 0 || k == 0) {
            return;
        }
        int[] heapIndex = new int[nums.length];
        int[] heap = new int[k];
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            heap[i] = i;
            heapIndex[i] = i;
        }
        for (int j = (k-1)/2; j >= 0 ; j--) {
            adjustHeap(heap, nums, heapIndex, j);
        }
        res[0] = nums[heap[0]];
        for (int i = 1; i <= nums.length - k; i++) {
            heap[heapIndex[i-1]] = i + k - 1;
            heapIndex[i + k - 1] = heapIndex[i-1];
            int j = heapIndex[i + k - 1];
            while (j >= 0) {
                adjustHeap(heap, nums, heapIndex, j);
                if (j == 0) {
                    break;
                } else {
                    j = (j - 1)/2;
                }
            }
            res[i] = nums[heap[0]];
        }
        PrintUtil.printIntArray(res);

    }

    static void adjustHeap(int[] heap, int[] nums, int[] heapIndex, int parent) {
        int leftChild = parent * 2 + 1;
        int rightChild = leftChild + 1;
        if (leftChild >= heap.length) {
            return;
        }
        int maxChild = leftChild;
        if (rightChild < heap.length) {
            if (nums[heap[rightChild]] > nums[heap[leftChild]]) {
                maxChild = rightChild;
            }
        }
        if (nums[heap[maxChild]] > nums[heap[parent]]) {
            swapHeap(heap, heapIndex, maxChild, parent);
            adjustHeap(heap, nums, heapIndex, maxChild);
        }
    }

    static void swapHeap(int[] heap, int[] heapIndex, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
        heapIndex[heap[index1]] = index1;
        heapIndex[heap[index2]] = index2;
    }
}
