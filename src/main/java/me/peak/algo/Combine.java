package me.peak.algo;

import java.util.*;

public class Combine {

    /**
     * C(m,n) 输出所有可能的组合
     */
    public static void main(String[] args) {
        List<List<Integer>> res = getAllCombine(5,3);
        for (List<Integer> list : res) {
            PrintUtil.printIntList(list);
        }
    }

    static List<List<Integer>> getAllCombine(int totalCount, int targetCount) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        addCombine(res, stack, totalCount, targetCount, 0);
        return res;
    }

    static void addCombine(List<List<Integer>> res, Stack<Integer> stack, int totalCount, int targetCount, int currentIndex) {
        if (stack.size() == targetCount) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = currentIndex; i < totalCount; i++) {
            if (!stack.contains(i)) {
                stack.add(i);
                addCombine(res, stack, totalCount, targetCount, i+1);
                stack.pop();
            }
        }
    }
}
