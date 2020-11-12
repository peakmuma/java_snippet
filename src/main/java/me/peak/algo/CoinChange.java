package me.peak.algo;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1,2,10,20,50,100};
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " " + coinChange(coins, i));
        }
    }

    static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin == i) {
                    dp[i] = 1;
                    break;
                } else if (coin < i && dp[i - coin] > 0) {
                    if (dp[i] == -1) {
                        dp[i] = dp[i - coin] + 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }
        }
        return dp[amount];
    }
}
