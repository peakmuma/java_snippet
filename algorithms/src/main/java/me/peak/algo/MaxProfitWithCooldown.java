package me.peak.algo;

public class MaxProfitWithCooldown {

    public static void main(String[] args) {
        int[] prices = {1,2};
        if (prices == null || prices.length < 2) {
            return;
        }
        int[] profits = new int[prices.length - 1];
        int[] containMax = new int[prices.length - 1];
        int[] lessMax = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            profits[i-1] = prices[i] - prices[i-1];
        }
        containMax[0] = Math.max(profits[0], 0);
        lessMax[0] = containMax[0];
        for (int i = 1; i < profits.length; i++) {
            if (i < 3) {
                containMax[i] = Math.max(containMax[i-1] + profits[i], profits[i]);
            } else {
                containMax[i] = Math.max(containMax[i-1] + profits[i], lessMax[i-3] + profits[i]);
            }
            lessMax[i] = Math.max(lessMax[i-1], containMax[i]);
        }
        System.out.println(lessMax[prices.length-2]);


    }
}
