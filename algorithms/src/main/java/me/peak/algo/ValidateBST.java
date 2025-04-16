package me.peak.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class ValidateBST {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        List<Integer> iterationRes = new ArrayList<>();

    }

    void iterate(List<Integer> iterationRes, TreeNode node) {
        if (node == null) {
            return;
        }
        iterate(iterationRes, node.left);
        iterationRes.add(node.val);
        iterate(iterationRes, node.right);
    }

    boolean check(List<Integer> iterationRes) {
        for (int i = 0; i < iterationRes.size() - 1; i++) {
            if (iterationRes.get(i) >= iterationRes.get(i+1)) {
                return false;
            }
        }
        return true;
    }


    static boolean isValid(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean left = true, right = true;
        if (node.left != null) {
            if (node.left.val > node.val) {
                return false;
            }
            left = isValid(node.left);
        }
        if (node.right != null) {
            if (node.right.val < node.val) {
                return false;
            }
            right = isValid(node.right);
        }
        return left && right;
    }
}
