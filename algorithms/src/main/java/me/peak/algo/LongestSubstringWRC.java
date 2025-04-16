package me.peak.algo;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWRC {

    public static void main(String[] args) {

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int longestEndIndex = 0;
        int longestLengthSize = 1;
        int tempLongestLengthSize = 0;
        Map<Character, Integer> charMaxIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer lastIndex = charMaxIndex.put(s.charAt(i), i);
            if (lastIndex == null) {
                tempLongestLengthSize++;
            } else if (i-1-tempLongestLengthSize > lastIndex){
                tempLongestLengthSize++;
            } else {
                tempLongestLengthSize = i - lastIndex;
            }
            if (tempLongestLengthSize > longestLengthSize) {
                longestLengthSize = tempLongestLengthSize;
                longestEndIndex = i;
            }
        }
        return longestLengthSize;
    }
}
