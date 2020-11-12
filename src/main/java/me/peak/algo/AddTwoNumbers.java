package me.peak.algo;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(), l2 = new ListNode();
        int sum;
        ListNode res = new ListNode(0);
        ListNode l3 = res;
        ListNode prev = null;
        while (l1 != null || l2 != null) {
            sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += l3.val;
            prev = l3;
            l3.val = sum % 10;
            l3.next = new ListNode(0);
            l3 = l3.next;
            l3.val = sum / 10;
        }
        if (l3.val == 0 && res.val != 0) {
            prev.next = null;
        }

    }
}
