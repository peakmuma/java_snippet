package me.peak.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/scramble-string/
 */
public class ScrambleString {

    public static void main(String[] args) {
        System.out.println(isScramble("a","a"));
        System.out.println(isScramble("a","b"));
        System.out.println(isScramble("aa","aa"));
        System.out.println(isScramble("aa","ab"));
        System.out.println(isScramble("ab","ba"));
        System.out.println(isScramble("abc","bca"));
        System.out.println(isScramble("abc","cba"));
        System.out.println(isScramble("abc","cab"));
        System.out.println(isScramble("abc","bac"));
        System.out.println(isScramble("abcde","caebd"));
        System.out.println(isScramble("great","rgeat"));
    }

    public static boolean isScramble(String s1, String s2) {
        System.out.println(s1 + " " + s2);
        List<boolean[][]> canScrambleList = new ArrayList<>(s1.length() + 1);
        canScrambleList.add(new boolean[0][0]);
        for (int length = 1; length <= s1.length(); length++) {
            int arrayLength = s1.length() - length + 1;
            boolean[][] dp = new boolean[arrayLength][arrayLength];
            canScrambleList.add(dp);
            for (int i = 0; i < arrayLength; i++) {
                for (int j = 0; j < arrayLength; j++) {
                    if (length == 1) {
                        dp[i][j] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        dp[i][j] = false;
                        for (int l = 1; l < length && !dp[i][j]; l++) {
                            boolean[][] ldp = canScrambleList.get(l);
                            boolean[][] rdp = canScrambleList.get(length - l);
                            dp[i][j] = (ldp[i][j] && rdp[i+l][j+l]) ||
                                    (ldp[i][j+length-l] && rdp[i+l][j]);
                        }
                    }
                }
            }
        }
        return canScrambleList.get(s1.length())[0][0];
    }

}
