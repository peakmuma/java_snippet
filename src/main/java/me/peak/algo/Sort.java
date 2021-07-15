package me.peak.algo;

public class Sort {
    public static void main(String[] args) {
        int[] nums = new int[] {5,6,4,3,7,8,11,-1,5,7,0};
        mergeSort(nums);
        PrintUtil.printIntArray(nums);
    }

    public static void mergeSort(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
    }

    public static void mergeSort(int[] num, int start, int end, int[] temp) {
        if (end == start) {
            return;
        } else if (end - start == 1) {
            merge(num, start, end, end, temp);
            return;
        }
        int mid = start + (end - start)/2;
        mergeSort(num, start, mid - 1, temp);
        mergeSort(num, mid, end, temp);
        merge(num, start, mid, end, temp);
    }

    public static void merge(int[] num, int start1, int start2, int end, int[] temp) {
        if (end <= start1) {
            return;
        }
        int i = start1, j = start2, k = start1;
        while( i < start2 && j <= end) {
            if(num[i] < num[j])
                temp[k++] = num[i++];
            else
                temp[k++] = num[j++];
        }
        while(i < start2) {
            temp[k++] = num[i++];
        }
        while(j <= end) {
            temp[k++] = num[j++];
        }
        System.arraycopy(temp, start1, num, start1, end + 1 - start1);
    }




    public static void quickSort(int[] nums) {

    }

    public static void heapSort(int[] nums) {

    }
}
