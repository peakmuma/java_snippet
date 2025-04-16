package me.peak.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 题目链接 https://leetcode-cn.com/problems/group-anagrams/
 */
public class GroupAnagrams {
    public static void main(String[] args) {
//        if (strs.length == 0) return new ArrayList();
//        Map<String, List> ans = new HashMap<String, List>();
//        int[] count = new int[26];
//        for (String s : strs) {
//            Arrays.fill(count, 0);
//            for (char c : s.toCharArray()) count[c - 'a']++;
//
//            StringBuilder sb = new StringBuilder("");
//            for (int i = 0; i < 26; i++) {
//                sb.append('#');
//                sb.append(count[i]);
//            }
//            String key = sb.toString();
//            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
//            ans.get(key).add(s);
//        }
//        return new ArrayList(ans.values());
        String[] strs = {"fin","ell"};
        HashMap<String, List<String>> signMap = new HashMap<>();
        for (String str : strs) {
            String sign = getSign(str);
            signMap.putIfAbsent(sign, new ArrayList<>());
            signMap.get(sign).add(str);
        }
        List<List<String>> s = new ArrayList<>();
        for (String sign : signMap.keySet()) {
            s.add(signMap.get(sign));
        }
        for (List<String> strList : s) {
            for (String str : strList) {
                System.out.println(str);
            }
            System.out.println();
        }
    }

    private static String getSign (String s) {
        String sign = s.length() + "_";
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            sum += c * c;
        }
        return sign + sum;
    }

}
