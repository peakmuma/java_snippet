package me.peak.algo;


import java.util.*;

/**
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/
 */
//示例 1：
//输入：s = "()())()"
//输出：["(())()","()()()"]
//
//示例 2：
//输入：s = "(a)())()"
//输出：["(a())()","(a)()()"]
//
//示例 3：
//输入：s = ")("
//输出：[""]
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
//        foreach(5,3);
//        int[] arr = new int[]{0,0,0};
//        getNext(arr, 5);
//        PrintUtil.printIntArray(arr);
//        while (getNext(arr, 5) == 1) {
//            PrintUtil.printIntArray(arr);
//        }
        PrintUtil.printStrList(removeInvalidParentheses("()"));
        PrintUtil.printStrList(removeInvalidParentheses("(a)"));
        PrintUtil.printStrList(removeInvalidParentheses(")("));
        PrintUtil.printStrList(removeInvalidParentheses("(()(())(()"));



    }

    public static List<String> removeInvalidParentheses(String s) {
        int rightCount = 0, extraRightCount = 0, mostRightIndex = -1, 
            leftCount = 0, extraLeftCount = 0, mostLeftIndex = s.length();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (!stack.empty()) {
                    stack.pop();
                } else {
                    extraRightCount++;
                    mostRightIndex = i;
                }
            }
        }
        while (!stack.empty()) {
            mostLeftIndex = stack.pop();
            extraLeftCount++;
        }


        HashSet<String> resSet = new HashSet<>();
        int[] rightIndexArray = new int[extraRightCount];
        int[] leftIndexArray = new int[extraLeftCount];
        for (int i = 0; i < leftIndexArray.length; i++) {
            leftIndexArray[i] = i+1;
        }
        do {
            for (int i = 0; i < rightIndexArray.length; i++) {
                rightIndexArray[i] = i+1;
            }
            do {
                StringBuilder resSb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(' && i >= mostLeftIndex) {
                        if (contain(leftIndexArray, leftCount)) {
                            continue;
                        }
                    } else if (s.charAt(i) == ')' && i <= mostRightIndex) {
                        if (contain(rightIndexArray, rightCount)) {
                            continue;
                        }
                    }
                    resSb.append(s.charAt(i));
                }
                resSet.add(resSb.toString());
            } while (getNext(rightIndexArray, extraRightCount) == 1);
        } while (getNext(leftIndexArray, extraLeftCount) == 1);
        return new ArrayList<>(resSet);
    }

    private static int getNext(int[] arr, int n) {
        int i = arr.length - 1;
        while (i >= 0 && arr[i] == n - arr.length + i + 1) {
            i--;
        }
        if (i < 0) {
            return 0;
        }
        arr[i] = arr[i]+1;
        while (i < arr.length - 1) {
            i++;
            arr[i] = arr[i-1]+1;
        }
        return 1;
    }

    private static boolean contain(int[] nums, int target) {
        for (int i: nums) {
            if (i== target)  return true;
        }
        return false;
    }

    //以下是一个遍历n个数取m个数字的所有方法
    private static void foreach(int n, int m) {
        int[] arr = new int[m];
        //i表示arr数组下标， j表示数组里的最大值
        int i = 0, j = 0;
        while (true) {
            //初始情况下，填充
            while (i < m && j < n) {
                arr[i++] = j++;
            }
            //i表示arr数组的下标，当填满的时候，表示数组里数据可用
            if (i == m) {
                PrintUtil.printIntArray(arr);
            }
            //下标向左回溯
            i--;
            if (j == n) {
                //数组里最大的值已经等于n了，需要继续回溯一位
                i--;
                if (i >= 0) {
                    //重置一下数组里的最大值
                    j = arr[i] + 1;
                } else {
                    //如果这一步的回溯超出了数组边界，表示已经遍历完了
                    break;
                }
            }
        }
    }
}
