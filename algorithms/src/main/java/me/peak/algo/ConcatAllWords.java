package me.peak.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import me.peak.utils.PrintUtil;

/**
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
 */
public class ConcatAllWords {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[] {"foo","bar"};
//        PrintUtil.printIntList(findSubstring(s, words));
//        s = "wordgoodgoodgoodbestword";
//        words = new String[] {"word","good","best","word"};
//        PrintUtil.printIntList(findSubstring(s, words));
//        s = "barfoofoobarthefoobarman";
//        words = new String[] {"bar","foo","the"};
//        PrintUtil.printIntList(findSubstring(s, words));
//        s = "wordgoodgoodgoodbestword";
//        words = new String[] {"word","good","best","good"};
//        PrintUtil.printIntList(findSubstring(s, words));
        s = "a";
        words = new String[] {"a","a"};
        PrintUtil.printIntList(findSubstring(s, words));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        int[] charCount = new int[26];
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        int charNum = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                charCount[words[i].charAt(j) - 'a'] = charCount[words[i].charAt(j) - 'a'] + 1;
                charNum++;
            }
            wordCountMap.put(words[i], wordCountMap.getOrDefault(words[i], 0) + 1);
        }
        HashSet<Integer> nonZeroChar = new HashSet<>();
        for (int i = 0; i < charNum && i < s.length(); i++) {
            int charIndex= s.charAt(i) - 'a';
            charCount[charIndex] = charCount[charIndex] - 1;
            if (charCount[charIndex] == 0) {
                nonZeroChar.remove(charIndex);
            } else {
                nonZeroChar.add(charIndex);
            }
        }
        List<Integer> res = new ArrayList<>();

        for (int begin = 0, end = charNum; end <= s.length(); begin++, end++) {
            if (nonZeroChar.isEmpty() && isMatch(s, begin, words[0].length(), words.length, new HashMap<>(wordCountMap))) {
                res.add(begin);
            }
            int charIndex = s.charAt(begin) - 'a';
            charCount[charIndex] = charCount[charIndex] + 1;
            if (charCount[charIndex] == 0) {
                nonZeroChar.remove(charIndex);
            } else {
                nonZeroChar.add(charIndex);
            }
            if (end < s.length()) {
                charIndex = s.charAt(end) - 'a';
                charCount[charIndex] = charCount[charIndex] - 1;
                if (charCount[charIndex] == 0) {
                    nonZeroChar.remove(charIndex);
                } else {
                    nonZeroChar.add(charIndex);
                }
            }
        }
        return res;
    }

    public static boolean isMatch(String s, int begin, int wordLength, int wordCount, HashMap<String, Integer> wordCountMap) {
        for (int i = 0; i < wordCount; i++, begin += wordLength) {
            String str = s.substring(begin, begin + wordLength);
            if (!wordCountMap.containsKey(str)) {
                return false;
            }
            int currWordCount = wordCountMap.get(str);
            currWordCount--;
            if (currWordCount == 0) {
                wordCountMap.remove(str);
            } else {
                wordCountMap.put(str, currWordCount);
            }
        }
        return wordCountMap.isEmpty();
    }



}
