package com.gsh.leetcode;

/**
 * 反转一个单链表
 *
 * @Author: gsh
 * @Date: Created in 2018/10/25 10:37
 * @Description:
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre, next, temp;

        pre = head;
        next = head.next;
        head.next = null;
        while (next != null) {
            temp = next.next;
            next.next = pre;
            pre = next;
            next = temp;
        }
        head = pre;
        return head;


    }
}
