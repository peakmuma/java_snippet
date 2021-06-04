package me.peak.algo;

public class MinCubeCount {
    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(i + " " + getMinCubeCount(i));
        }
    }

    public static int getMinCubeCount(int n) {
        int[] cube = new int[n+1];
        for (int i = 1; i <= n; i++) {
            cube[i] = i * i * i;
            if (cube[i] >= n) {
                break;
            }
        }
        int[] count = new int[n+1];
        count[0] = 0;
        for (int i = 1; i <= n; i++) {
            int minCount = i;
            int j = 1;
            while (j < n + 1 && cube[j] <= i) {
                minCount = Math.min(minCount, count[i-cube[j]] + 1);
                j++;
            }
            count[i] = minCount;
        }
        return count[n];
    }
}
