package me.peak.algo;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-ladder/
 */
public class WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] wordArr = new String[]{"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(wordArr);
        System.out.println(ladderLength(beginWord, endWord, wordList));
        beginWord = "a"; endWord = "c";
        wordArr = new String[]{"a","b","c"};
        wordList = Arrays.asList(wordArr);
        System.out.println(ladderLength(beginWord, endWord, wordList));
        beginWord = "hot"; endWord = "dog";
        wordArr = new String[]{"hot","dog"};
        wordList = Arrays.asList(wordArr);
        System.out.println(ladderLength(beginWord, endWord, wordList));
        beginWord = "hog"; endWord = "cog";
        wordArr = new String[]{"cog"};
        wordList = Arrays.asList(wordArr);
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int target = wordList.indexOf(endWord);
        if (target < 0) {
            return 0;
        }
        boolean[][] canLadder = new boolean[wordList.size()][wordList.size()];
        for (int i = 0; i < wordList.size(); i++) {
            canLadder[i][i] = false;
            for (int j = i + 1; j < wordList.size(); j++) {
                canLadder[i][j] = canLadder(wordList.get(i), wordList.get(j));
            }
        }
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < i; j++) {
                canLadder[i][j] = canLadder[j][i];
            }
        }
        int[] dp = new int[wordList.size()];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (i == target) {
                dp[i] = 1;
            } else if (canLadder[target][i]) {
                queue.offer(i);
                dp[i] = 2;
            }
        }
        calcLength(canLadder, dp, queue);

        int length = dp.length + 2;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > 0 && (dp[i] + 1) < length && canLadder(beginWord, wordList.get(i))) {
                length = dp[i] + 1;
            }
        }
        if (length == dp.length + 2) {
            return 0;
        } else {
            return length;
        }
    }

    public static boolean canLadder(String source, String target) {
        if (source.length() != target.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != target.charAt(i)) count++;
        }
        return count == 1;

    }

    public static void calcLength(boolean[][] canLadder, int[]dp, Queue<Integer> queue) {
        while (!queue.isEmpty()) {
            int source = queue.poll();
            for (int i = 0; i < canLadder.length; i++) {
                if (canLadder[source][i] && dp[i] == 0) {
                    dp[i] = dp[source] + 1;
                    queue.add(i);
                }
            }
        }
    }

}
