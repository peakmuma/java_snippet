package me.peak.algo;

public class SortLink {

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void main(String[] args) {

        Node head = new Node(-1);
        head.next = new Node(3);
        head.next.next = new Node(1);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(2);

        printNode(head);
        sort(head);
        printNode(head);

    }

    static void sort(Node root) {
        if (root == null || root.next == null) {
            return;
        }
        int size  = 0;
        Node node = root.next;
        while (node != null) {
            size++;
            node = node.next;
        }

        for (int i = size; i > 0; i--) {
            Node prev = root;
            Node current = root.next;

            Node maxPrev = root;
            int maxVal = current.val;

            //遍历得到最大值
            for (int j = 0; j < i - 1; j++) {
                prev = current;
                current = current.next;

                if (current.val > maxVal) {
                    //进行记录
                    maxPrev = prev;
                    maxVal = current.val;
                }
            }

            //当前节点和最大的交换
            Node last = current.next;
            Node max = maxPrev.next;

            if (max == current) {
                continue;
            }

            if (max.next == current) {
                maxPrev.next = current;
                current.next = max;
                max.next = last;
            } else {
                current.next = max.next;
                max.next = last;
                prev.next = max;
                maxPrev.next = current;
            }
        }
    }

    static void printNode(Node head) {
        Node n = head.next;
        while (n != null) {
            System.out.print(n.val + " ");
            n = n.next;
        }
        System.out.println();
    }
}
