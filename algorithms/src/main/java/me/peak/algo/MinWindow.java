package me.peak.algo;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class MinWindow {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        HashMap<Character, Integer> sCountMap = new HashMap<>();
        sCountMap.put(s.charAt(0), 1);
        HashMap<Character, Integer> tCountMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Integer count = tCountMap.getOrDefault(t.charAt(i), 0);
            tCountMap.put(t.charAt(i), count + 1);
        }
        HashMap<Character, Integer> lessCharMap = new HashMap<>();
        for (Character c : tCountMap.keySet()) {
            if (sCountMap.getOrDefault(c, 0) < tCountMap.getOrDefault(c, 0)) {
                lessCharMap.put(c, 0);
            }
        }
        int minLength = s.length() + 1, minStart = 0, minEnd = 0, length = 0;
        int i = 0, j = 1;
        while (j <= s.length() && i < j) {
            if (lessCharMap.isEmpty()) {
                length = j - i;
                if (length < minLength) {
                    minLength = length;
                    minStart = i;
                    minEnd = j;
                }
                char c = s.charAt(i);
                Integer count = sCountMap.get(c);
                if (tCountMap.getOrDefault(c, 0) > count - 1) {
                    lessCharMap.put(c, 0);
                }
                sCountMap.put(c, count - 1);
                i++;
            } else if (j < s.length()) {
                char c = s.charAt(j);
                Integer count = sCountMap.getOrDefault(c, 0);
                sCountMap.put(c, count + 1);
                if (tCountMap.getOrDefault(c, 0) <= count + 1) {
                    lessCharMap.remove(c);
                }
                j++;
            } else {
                break;
            }
        }
        System.out.println(s.substring(minStart, minEnd));
    }
}
