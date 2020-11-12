package me.peak.algo;

/**
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 */
public class UniqueBST {
    public static void main(String[] args) {
        int n = 19;
        int[] count = new int[n+1];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i <= n; i++) {
            calc(count, i);
        }
        PrintUtil.printIntArray(count);
    }

    static void calc(int[] count, int n) {
        for (int i = 1; i <= n; i++) {
            count[n] += count[i-1] * count[n-i];
        }
    }
}
