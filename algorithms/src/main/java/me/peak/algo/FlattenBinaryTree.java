package me.peak.algo;

/*
https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);


    }

    static TreeNode flatTree(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            return node;
        }
        TreeNode rightTail = flatTree(node.right);
        TreeNode leftTail = flatTree(node.left);
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
        }
        node.left = null;
        return rightTail != null ? rightTail : leftTail;
    }

}
