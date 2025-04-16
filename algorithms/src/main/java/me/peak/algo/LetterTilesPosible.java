package me.peak.algo;

import java.util.*;

//https://leetcode.cn/problems/letter-tile-possibilities
public class LetterTilesPosible {

    public static void main(String[] args) {
        int res = numTilePossibilities("AAABBC");
        System.out.println(res);
    }

    public static int numTilePossibilities(String tiles) {
        LinkedList<Character> sb = new LinkedList<>();
        char[] charArray = tiles.toCharArray(); // 将字符串转换为字符数组
        Arrays.sort(charArray); // 对字符数组进行排序
        String sortedStr = new String(charArray); // 将排序后的字符数组转换回字符串
        Map<Character, Integer> endIndexMap = new HashMap<>();
        endIndexMap.put(sortedStr.charAt(sortedStr.length() - 1), sortedStr.length() - 1);
        for (int i = sortedStr.length() - 2; i >= 0; i--) {
            if (sortedStr.charAt(i) != sortedStr.charAt(i + 1)) {
                endIndexMap.put(sortedStr.charAt(i), i);
            }
        }
        int[] res = new int[]{0};
//        Set<String> res = new HashSet<>();
        print(sortedStr, 0, endIndexMap, sb, res);
        return res[0];
    }

    /*
    记录一下，整体思路还是回溯，sb 代表当前的临时结果，
    假设入参是AAABBC
    就考虑一次遍历 0个A，1个A，2个A，3个A的情况

     */
    static void print(String tiles, int index, Map<Character, Integer> endIndexMap, LinkedList<Character> sb, int[] res) {
        if (index == tiles.length()) {
            return;
        }
        Character c = tiles.charAt(index);
        int endIndex = endIndexMap.get(c);
        if (index == 0 || tiles.charAt(index -1) != tiles.charAt(index)) {
            //如果当前字符和前面的字符不同，不选择当前字符的情况
            print(tiles, endIndex + 1, endIndexMap, sb, res);
        }
        int start = sb.lastIndexOf(c);
        int end = sb.size();
        //遍历临时结果的所有槽位
        for (int i = start + 1; i < end; i++) {
            sb.add(i, c);
            res[0] = res[0] + 1;
            //遍历下一个字符 ，比如AAABBCC， 这里index = 0 的情况，这里直接选择B
            print(tiles, endIndex + 1, endIndexMap, sb, res);
            if (index != endIndex) {
                //遍历下一个字符，比如BBBCC，这里index = 0 的情况，这里选择第二个A
                print(tiles, index + 1, endIndexMap, sb, res);
            }
            sb.remove(i);
        }
        //最后一个槽位
        sb.add(c);
        res[0] = res[0] + 1;
        print(tiles, endIndex + 1, endIndexMap, sb, res);
        if (index != endIndex) {
            print(tiles, index + 1, endIndexMap, sb, res);
        }
        sb.remove(sb.size() - 1);
    }

    static String toString(LinkedList<Character> sb) {
        StringBuilder builder = new StringBuilder();
        for (Character ch : sb) {
            builder.append(ch);
        }
        return builder.toString();
    }

}
