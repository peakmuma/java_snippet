package me.peak.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        List<List<Integer>> res = new ArrayList<>();
        traversal(res, root, 0);
    }

    static void traversal(List<List<Integer>> res, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        traversal(res, node.left, level + 1);
        traversal(res, node.right, level + 1);
    }
}
