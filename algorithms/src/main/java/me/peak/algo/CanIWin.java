package me.peak.algo;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.cn/problems/can-i-win/description/
public class CanIWin {
    public static void main(String[] args) {
        System.out.println(canIWin(10, 11));
    }

    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        Map<String, Map<Boolean, Boolean>> res = new HashMap<>();
        StringBuilder canUseNum = new StringBuilder();
        for (int i = 0; i < maxChoosableInteger; i++) {
            canUseNum.append('1');
        }
        return canIWin(true, canUseNum, 0, desiredTotal, res);
    }

    static boolean canIWin(Boolean first, StringBuilder canUseNum, int currentTotal, int desiredTotal, Map<String, Map<Boolean, Boolean>> res) {
        String key = canUseNum.toString();
        if (!res.containsKey(key)) {
            res.put(key, new HashMap<>());
        }
        if (res.get(key).containsKey(first)) {
            return res.get(key).get(first);
        }
        if (currentTotal >= desiredTotal) {
            res.get(key).put(first, !first);
            return !first;
        }
        for (int i = 0; i < canUseNum.length(); i++) {
            if (canUseNum.charAt(i) == '1') {
                currentTotal += (i+1);
                canUseNum.setCharAt(i, '0');
                canIWin(!first, canUseNum, currentTotal, desiredTotal, res);
                currentTotal -= (i+1);
                canUseNum.setCharAt(i, '1');
            }
        }
        res.get(key).put(first, false);
        return false;
    }

}
