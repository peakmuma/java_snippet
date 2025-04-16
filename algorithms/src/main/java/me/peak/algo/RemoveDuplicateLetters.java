package me.peak.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://leetcode.cn/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {


    public static void main(String[] args) {
    }



    public static String removeDuplicateLetters(String s) {
        TreeMap<Character, List<Integer>> indexMap = new TreeMap<>();
        TreeSet<Integer> resIndexSet = new TreeSet<>((a,b) -> b-a);
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!indexMap.containsKey(c)) {
                indexMap.put(c, new ArrayList<>());
            }
            indexMap.get(c).add(i);
        }
        for (Character c : indexMap.keySet()) {
            List<Integer> indexList = indexMap.get(c);
            if (indexList.size() == 1) {
                resIndexSet.add(indexList.get(0));
            } else {
                int currentCharIndex = -1;
                for(Integer end : resIndexSet) {
                    currentCharIndex = findFirstGTTarget(indexList, end, 0, indexList.size() - 1);
                    if (currentCharIndex != -1) {
                        break;
                    }
                }
                if (currentCharIndex == -1) {
                    currentCharIndex = indexList.get(0);
                }
                resIndexSet.add(currentCharIndex);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer index : resIndexSet.descendingSet()) {
            sb.append(s.charAt(index));
        }
        return sb.toString();
    }

    private static int findFirstGTTarget(List<Integer> indexList, Integer target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (indexList.get(mid) > target) {
            if (mid == 0 || indexList.get(mid - 1) <= target) {
                return indexList.get(mid);
            } else {
                return findFirstGTTarget(indexList, target, start, mid - 1);
            }
        } else if (indexList.get(mid) < target) {
            return findFirstGTTarget(indexList, target, mid + 1, end);
        }
        return -1;
    }

    //该方法的思路是: 从签到后遍历字符串，不断的添加字符进去，当字符没出现在结果里，直接添加，当字符已经在结果里了，那么就需要在之前的位置和当前的位置二选一
    //如果这个字符在结果里比后面的字符大，就选当前位置，否则就保留前面的位置
    //但是这种思路有个问题，就是最开始判断的时候，保留了前面的位置，但是这个字符后面的字符的位置后面变化了，结果就不正确了。
    // 例如 bcabc  中，遍历到第二个b的时候，按照算法选择了第一个b，但是当c执行完之后，b应该保留第二个。  因此该过程有问题。
    public static String removeDuplicateLettersUnRight(String s) {
        HashMap<Character, Node> res = new HashMap<>();
        Node head = null;
        Node tail = null;
        for (int i = 0; i < s.length(); i++) {
            Character currentChar = s.charAt(i);
            if (!res.containsKey(currentChar)) {
                Node node = new Node(currentChar);
                if (tail != null) {
                    tail.next = node;
                    node.prev = tail;
                }
                tail = node;
                if (head == null) {
                    head = node;
                }
                res.put(currentChar, node);
            } else {
                Node node = res.get(currentChar);
                if (node.next != null && node.next.value.compareTo(currentChar) < 0) {
                    node.next.prev = node.prev;
                    if (node.prev != null) {
                        node.prev.next = node.next;
                    } else {
                        head = node.next;
                    }
                    node.prev = tail;
                    node.next = null;
                    tail.next = node;
                    tail = node;
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        Node node = head;
        while (node != null) {
            sb.append(node.value);
            node = node.next;
        }
        return sb.toString();
    }

    static class Node {
        Character value;
        Node prev;
        Node next;

        public Node(Character value) {
            this.value = value;
        }
    }
}
