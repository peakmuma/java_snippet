package me.peak.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagrams {
    public static void main(String[] args) {
        String s = "cbaebabacd"; String p = "abc";
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null || s.length() == 0 || p.length() == 0) {
            return;
        }
        Map<Character, Integer> letterMap = new HashMap();
        Map<Character, Integer> lessMap = new HashMap();
        for (int i = 0; i < p.length(); i++) {
            Character c = p.charAt(i);
            letterMap.put(c, letterMap.getOrDefault(c, 0) + 1);
            lessMap.put(c, lessMap.getOrDefault(c, 0) + 1);
        }
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (letterMap.containsKey(c)) {
                if (length == 0) {
                    copyMap(letterMap, lessMap);
                    length++;
                } else if (length < p.length()) {
                    length++;
                } else {
                    Character outChar = s.charAt(i - p.length());
                    int count1 = lessMap.getOrDefault(outChar, 0) + 1;
                    if (count1 == 0) {
                        lessMap.remove(outChar);
                    } else {
                        lessMap.put(outChar, count1);
                    }
                }
                int count = lessMap.getOrDefault(c, 0) - 1;
                if (count == 0) {
                    lessMap.remove(c);
                } else {
                    lessMap.put(c, count);
                }

                if (length == p.length() && lessMap.isEmpty()) {
                    res.add(i - p.length() + 1);
                }
            } else {
                length = 0;
                lessMap.clear();
            }
        }
        PrintUtil.printIntList(res);
    }

    static void copyMap(Map<Character, Integer> letterMap, Map<Character, Integer> lessMap) {
        if (!lessMap.isEmpty()) {
            return;
        }
        for (Character c : letterMap.keySet()) {
            lessMap.putIfAbsent(c, letterMap.get(c));
        }
    }


}
