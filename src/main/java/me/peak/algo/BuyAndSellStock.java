package me.peak.algo;

public class BuyAndSellStock {
    public static void main(String[] args) {
        int[] nums = new int[] {3,2,6,5,0,3,0,5};
        System.out.println(maxProfit(1, nums));
        System.out.println(maxProfit(2, nums));
        System.out.println(maxProfit(3, nums));
    }

    public static int maxProfit(int k, int[] prices) {
        int[] max = new int[prices.length/2];
        int[] min = new int[prices.length/2];
        int length = 0;
        int currentMin = prices[0];
        for (int i = 1; i < prices.length - 1; i++) {
            if (prices[i] > prices[i-1] && prices[i] >= prices[i + 1]) {
                max[length] = prices[i];
                min[length] = currentMin;
                currentMin = prices[i];
                length++;
            } else if (prices[i] < currentMin) {
                currentMin = prices[i];
            }
        }
        if (prices[prices.length - 1] > prices[prices.length - 2]) {
            max[length] = prices[prices.length - 1];
            min[length] = currentMin;
            length++;
        }
        int maxTradeTime = Math.min(k, length);
        int profit = 0;
        //计算一次交易的情况下，最早从i买入，必须从j卖出的最大利润
        int[][] oneTradeMaxProfit = new int[length][length];
        for (int i = 0; i < length; i++) {
            currentMin = min[i];
            for (int j = i; j < length; j++) {
                currentMin = Math.min(currentMin, min[j]);
                oneTradeMaxProfit[i][j] = max[j] - currentMin;
                profit = Math.max(oneTradeMaxProfit[i][j], profit);
            }
        }
        //计算交易t次，最后一次在i卖出的最大利润
        int[][] dp = new int[length][maxTradeTime+1];
        for (int i = 0; i < length; i++) {
            dp[i][1] = oneTradeMaxProfit[0][i];
        }
        for (int t = 2; t <= maxTradeTime; t++) {
            for (int i = t - 1; i < length; i++) {
                for (int l = t - 2; l < i; l++) {
                    dp[i][t] = Math.max(dp[i][t], dp[l][t-1] + oneTradeMaxProfit[l+1][i]);
                    profit = Math.max(dp[i][t], profit);
                }
            }
        }
        return profit;
    }

}
