package me.peak.algo.sort;

public class MergeNode {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3, null);
        ListNode node2 = new ListNode(2, node1);
        ListNode node3 = new ListNode(1, node2);
        ListNode node4 = new ListNode(4, node3);
        sortList(node4);


    }

    public static ListNode sortList(ListNode head) {
        return mergeSort(head, null);
    }

    static ListNode mergeSort(ListNode head, ListNode tail) {
        if (head == tail) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null && fast != tail) {
                fast = fast.next;
            }
        }

        ListNode head1 = mergeSort(head, slow);
        ListNode head2 = mergeSort(slow.next, tail);

        ListNode newHead = merge(head1, head2);
        return newHead;
    }

    static ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }

        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }

        return newHead.next;

    }
}
