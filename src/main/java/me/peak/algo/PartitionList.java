package me.peak.algo;

import java.util.List;

//https://leetcode.cn/problems/partition-list/?envType=problem-list-v2&envId=linked-list
public class PartitionList {

    /**
     * Definition for singly-linked list.
     */
     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0);
        ListNode smallTail = smallHead;
        ListNode largeHead = new ListNode(0);
        ListNode largeTail = largeHead;

        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                smallTail.next = current;
                smallTail = current;
            } else {
                largeTail.next = current;
                largeTail = current;
            }
            current = current.next;
        }
        smallTail.next = largeHead.next;
        return smallHead.next;



    }

}
