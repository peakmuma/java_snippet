package me.peak.algo;

/**
 * https://leetcode-cn.com/problems/wildcard-matching/
 */
public class WildcardMatching {

    public static void main(String[] args) {
//        System.out.println(isMatch("",""));
//        System.out.println(isMatch("a","a"));
//        System.out.println(isMatch("a","b"));
//        System.out.println(isMatch("a","?"));
//        System.out.println(isMatch("ab","a*"));
//        System.out.println(isMatch("ab","*"));
//        System.out.println(isMatch("abcd","a*b*c*d*"));
//        System.out.println(isMatch("abcdefg","abc*d*ef"));
        System.out.println(isMatch("aab","c*ab"));
    }

    public static boolean isMatch(String s, String p) {
        System.out.println("s="+ s + "  p=" + p);
        if (s == null || p == null) {
            return true;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = false;
        }
        for (int i = 1; i <= p.length(); i++) {
            dp[0][i] = p.charAt(i - 1) == '*' && dp[0][i-1];
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length() ; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i-1][j-1] && (p.charAt(j-1) == '?' || p.charAt(j-1) == s.charAt(i-1));
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
