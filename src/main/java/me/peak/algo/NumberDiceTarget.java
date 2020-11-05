package me.peak.algo;

import org.bouncycastle.asn1.x509.qualified.RFC3739QCObjectIdentifiers;

public class NumberDiceTarget {
    public static void main(String[] args) {
        int d = 30, f = 30, target = 500;
//        int d = 1, f = 1, target = 1;
        if (d <= 0 || f <= 0 || target <= 0) {
            return;
        }
        int model = 1000000007, preSum = 0;
        int[][] count = new int[d][target + 1];
        for (int i = 1; i <= f && i <= target; i++) {
            count[0][i] = 1;
        }
        for (int i = 1; i < d; i++) {
            preSum += f;
            for (int j = 1; j <= f; j++) {
                for (int k = i; k + j <= target && k <= preSum; k++) {
                    count[i][k+j] += count[i-1][k];
                    if (count[i][k+j] > model) {
                        count[i][k+j] %= model;
                    }
                }
            }
        }
        int res = count[d-1][target];
//        int res = number(0, d, f, target);
        System.out.println(res);


    }

    static int number(int startIndex, int endIndex, int endNumber, int target) {
        int count = endIndex - startIndex;
        if (target < count || target > endNumber * count) {
            return 0;
        }
        if (count == 1) {
            return 1;
        }
        int res = 0;
        for (int i = 1; i <= endNumber; i++) {
            res += number(startIndex + 1, endIndex, endNumber, target - i);
            if (res > 1000000007) {
                res %= 1000000007;
            }
        }
        return res;
    }
}
