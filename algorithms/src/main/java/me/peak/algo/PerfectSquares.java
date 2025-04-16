package me.peak.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.peak.utils.PrintUtil;

public class PerfectSquares {
    public static void main(String[] args) {
        int n = 20;
        List<Integer> squareList = new ArrayList<>();
        int[] numSquare = new int[n + 1];
        Arrays.fill(numSquare, n);
        numSquare[0] = 0;
        for (int i = 1; i <= n; i++) {
            int square = i * i;
            if (square < n) {
                squareList.add(square);
            } else if (square == n) {
                return;
            } else {
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            int j = 0;
            while (j < squareList.size() && i >= squareList.get(j)) {
                numSquare[i] = Math.min(numSquare[i], numSquare[i - squareList.get(j)] + 1 );
                j++;
            }
        }
        PrintUtil.printIntArray(numSquare);
    }
}

