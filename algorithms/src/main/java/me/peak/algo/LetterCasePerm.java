package me.peak.algo;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePerm {

    public static void main(String[] args) {
        letterCasePermutation("a1b2");
    }

    public static List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        letterCasePermutation(s, 0, stringBuilder, res);
        return res;
    }

    public static void letterCasePermutation(String s, int index, StringBuilder stringBuilder, List<String> res) {
        if (stringBuilder.length() == s.length()) {
            res.add(stringBuilder.toString());
            return;
        }

        char c = s.charAt(index);
        if (Character.isLetter(c)) {
            stringBuilder.append(Character.toLowerCase(c));
            letterCasePermutation(s, index + 1, stringBuilder, res);
            stringBuilder.setLength(stringBuilder.length() - 1);
            stringBuilder.append(Character.toUpperCase(c));
            letterCasePermutation(s, index + 1, stringBuilder, res);
            stringBuilder.setLength(stringBuilder.length() - 1);
        } else {
            stringBuilder.append(c);
            letterCasePermutation(s, index + 1, stringBuilder, res);
        }
    }
}
