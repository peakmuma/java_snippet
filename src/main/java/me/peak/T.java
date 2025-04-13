package me.peak;

import java.util.HashSet;

public class T {
    //两个数字字符串相加，比如s1="123",s2="234",计算和。

    //输入一个字符串，输出最长子串，比如abcabcdbb,输出abcd

    public static void main(String[] args) {
        String s1 = "123";
        String s2 = "234";
        System.out.println(addStr(s1, s2));

        System.out.println(getLongSubStr("abcabcdbb"));
        System.out.println(getLongSubStr("abcabcdbabcdeefabcdefb"));
    }

    static String getLongSubStr(String s) {
        HashSet<Character> set = new HashSet<>();
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (set.add(c)) {
                res.append(c);
            }
        }
        return res.toString();
    }

    static String addStr (String s1, String s2) {
        StringBuffer res = new StringBuffer();

        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int tempJ = 0;
        while (i >=0 && j>=0) {
            int num1 = s1.charAt(i) - '0';
            int num2 = s2.charAt(j) - '0';
            int tempRes = num1 + num2 + tempJ;
            tempJ = tempRes / 10;
            res.append(tempRes % 10);
            i--;
            j--;
        }

        while (i >= 0) {
            int num1 = s1.charAt(i) - '0';
            int tempRes = num1 + tempJ;
            tempJ = tempRes / 10;
            res.append(tempRes % 10);
            i--;
        }

        while (j >= 0) {
            int num2 = s2.charAt(j) - '0';
            int tempRes = num2 + tempJ;
            tempJ = tempRes / 10;
            res.append(tempRes % 10);
            j--;
        }

        if (tempJ > 0) {
            res.append(tempJ);
        }

        return res.reverse().toString();
    }
}
