package me.peak.algo;

/**
 * 最长回文子串, 题目链接 https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String a = "cbbd";
        System.out.println(longestPalindrome(a));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        int maxLength = 0;
        int maxStart = 0;
        int maxEnd = 1;
        for (int i = 0; i < s.length(); i++) {
            int length = getMaxPalindromeLength(s, i, i+1);
            if (length * 2 > maxLength) {
                maxStart = i - length + 1;
                maxEnd = i + length + 1;
                maxLength = length * 2;
            }
            length = getMaxPalindromeLength(s, i, i);
            if ( 2 * length - 1 > maxLength) {
                maxLength = 2 * length - 1;
                maxStart = i - length + 1;
                maxEnd = i + length;
            }
        }
        return s.substring(maxStart, maxEnd);

    }

    private static int getMaxPalindromeLength(String s, int j, int k) {
        int length = 0;
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            length++;
            j--;
            k++;
        }
        return length;
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
            start++;
            end--;
        }
        return start >= end;
    }
}
