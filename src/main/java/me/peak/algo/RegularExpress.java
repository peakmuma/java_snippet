package me.peak.algo;

/*
https://leetcode-cn.com/problems/regular-expression-matching/
 */
public class RegularExpress {
    public static void main(String[] args) {
        System.out.println(isMatch("abc", "abc"));
        System.out.println(isMatch("abc", "abd"));
        System.out.println(isMatch("abc", "ab"));
        System.out.println(isMatch("abd", "ab."));
        System.out.println(isMatch("aaa", "a*a"));
        System.out.println(isMatch("abc", "a*b"));
        System.out.println(isMatch("abc", "a*b*c"));
        System.out.println(isMatch("abc", "c*a*bc"));
        System.out.println(isMatch("abcb", "a*c"));
        System.out.println(isMatch("abcb", "a.*b"));
        System.out.println(isMatch("abcb", "a.*cd*b"));
        System.out.println(isMatch("abcdbc", "a*bc"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));
        System.out.println(isMatch("mississippi", "mis*is*ip*."));
        System.out.println(isMatch("a", "ab*"));
        System.out.println(isMatch("a", "ab*c*"));
        System.out.println(isMatch("a", "c*a"));
        System.out.println(isMatch("a", "c*b*a"));
        System.out.println(isMatch("abcccc", "abcc*"));
        System.out.println(isMatch("aaaaa", "a*"));
        System.out.println(isMatch("aaaaa", "b*"));
    }

    public static boolean isMatch(String s, String p) {
        System.out.println("s = " + s + ", p = " + p);
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j-1) == '*' && j > 1) {
                dp[0][j] = dp[0][j - 2];
            }else {
                dp[0][j] = false;
            }
        }
        for (int j = 1; j <= p.length(); j++) {
            for (int i = 1; i <= s.length(); i++) {
                if (p.charAt(j-1) != '*') {
                    dp[i][j] = (p.charAt(j-1) == '.' || s.charAt(i-1) == p.charAt(j-1)) && dp[i-1][j-1];
                } else if (j == 1) {
                    dp[i][j] = false;
                } else {
                    dp[i][j] = dp[i][j-2] || ( dp[i-1][j] && (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'));
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //以下使用递归思路实现的
    public static boolean isMatchRecurs(String s, String p) {
        System.out.println("s = " + s + ", p = " + p);
        if (s == null || p == null) {
            return false;
        }
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            char c = p.charAt(j);
            boolean isNextStar = j + 1 < p.length() && p.charAt(j + 1) == '*';
            if (!isNextStar) {
                if (c == '.') {
                    i++;
                    j++;
                } else {
                    if (s.charAt(i) != c) {
                        return false;
                    } else {
                        i++;
                        j++;
                    }
                }
            } else {
                j = j + 2;
                while (i < s.length()) {
                    if (isMatchRecurs(s.substring(i), p.substring(j))) {
                        return true;
                    } else if (c == '.' || s.charAt(i) == c) {
                        i++;
                    } else {
                        break;
                    }
                }
            }
        }
        if (i < s.length()) {
            return false;
        }
        while (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            j = j + 2;
        }
        return j == p.length();
    }

    //以下这个实现是按照自己对题目的理解实现
    // 其中.代表任意一个字符，*代表任一一个字符串
    public static boolean isMatchMy(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            char c = p.charAt(j);
            if (c == '.') {
                i++;
                j++;
            } else if (c != '*') {
                if (s.charAt(i) != c) {
                    return false;
                } else {
                    i++;
                    j++;
                }
            } else {
                j++;
                while (j < p.length() && p.charAt(j) == '*') {
                    j++;
                }
                if (j == p.length()) {
                    return true;
                }
                while (i < s.length() && !isMatchMy(s.substring(i), p.substring(j))) {
                    i++;
                }
                return i < s.length();
            }
        }
        return i == s.length() && j == p.length();
    }
}
