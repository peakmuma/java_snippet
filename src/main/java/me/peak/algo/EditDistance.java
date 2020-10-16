package me.peak.algo;

public class EditDistance {
    public static void main(String[] args) {
//        String word1 = "horse", word2 = "ros";
        String word1 = "intention", word2 = "execution";
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + 1;
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i][j]);
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1] + 1, dp[i][j]);
                }
            }
        }
        System.out.println(dp[word1.length()][word2.length()]);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();

        }
    }
}
