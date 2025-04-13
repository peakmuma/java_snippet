package jerry.test;

import lombok.val;

public class SkipList {

    private static final float ADD_LEVEL_PERCENT = 0.5f;
    private static final int MAX_LEVEL = 5;

    private Node head;

    public SkipList() {
        head = new Node(-1, MAX_LEVEL);
    }



    //找到最大的小于等于给定值的节点
    public Node findLTOrEQNode (int intervalStart) {
        int level = MAX_LEVEL - 1; //初始在最高层级遍历
        Node currentNode = head.next[level];  //
        while (currentNode != null) {
            if (currentNode.intervalStart == intervalStart) {
                return currentNode;  //如果相等，直接返回当前节点
            } else if (currentNode.intervalStart < intervalStart) {
                //如果当前节点小于给定值，并且当前层级的下个节点不为空，继续遍历下一个节点
                if (currentNode.next[level] != null) {
                    currentNode = currentNode.next[level];
                } else {
                    //如果当前层级就是最低了，那说明当前节点就是最大的小于给定值的节点，返回结果，否则遍历下一层
                    if (level == 0) {
                        return currentNode;
                    } else {
                        level--;
                    }
                }
            } else {
                // 如果当前节点大于了给定值，
                if (level == 0) {
                    return currentNode.prev[0];
                } else {
                    currentNode = currentNode.prev[level];
                    level--;
                }
            }
        }
        return head;
    }

    public void insert(int value) {

    }

    public void delete (Node node) {

    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < ADD_LEVEL_PERCENT && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }


    public class Node {
        int intervalStart;
        int intensity;
        Node[] next;
        Node[] prev;
        int maxLevel;

        public Node(int intervalStart, int level) {
            this.intervalStart = intervalStart;
            next = new Node[level];
            prev = new Node[level];
            this.maxLevel = level;
        }
    }
}
