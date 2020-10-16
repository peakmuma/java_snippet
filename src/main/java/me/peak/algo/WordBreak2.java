package me.peak.algo;

import java.util.*;

public class WordBreak2 {
    public static void main(String[] args) {
//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String[] wordDictArray = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
        String s = "abcd";
        String[] wordDictArray = {"a","ab","b","c","d"};
        List<String> wordDict = Arrays.asList(wordDictArray);
        Map<Integer, List<String>> wordListMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                String subStr = s.substring(j, i+1);
                if (wordDict.contains(subStr)) {
                    if (j == 0 || wordListMap.get(j-1) != null) {
                        wordListMap.putIfAbsent(i, new ArrayList<>());
                        wordListMap.get(i).add(subStr);
                    }
                }
            }
        }
        List<List<String>> resList = new ArrayList<>();
        geneRes(wordListMap, s.length() - 1, resList, new ArrayList<>());
        List<String> res = new ArrayList<>();
        for (List<String> wordList : resList) {
            StringBuilder resSB = new StringBuilder();
            for (int i = wordList.size() - 1; i >= 0; i--) {
                if (resSB.length() > 0) {
                    resSB.append(" ");
                }
                resSB.append(wordList.get(i));
            }
            res.add(resSB.toString());
        }
        for (String s1 : res) {
            System.out.println(s1);
        }


    }

    private static void geneRes(Map<Integer, List<String>> wordListMap, int index, List<List<String>> resList, List<String> res) {
        if (index < 0) {
            resList.add(res);
            return;
        }
        List<String> wordList = wordListMap.get(index);
        if (wordList != null) {
            for (String word : wordList) {
                List<String> res1 = new ArrayList<>(res);
                res1.add(word);
                geneRes(wordListMap, index - word.length(), resList, res1);
            }
        }
    }
}
