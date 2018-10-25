package com.gsh.leetcode;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/25 10:54
 * @Description:
 */
public class ReverseLinkedListTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode node = reverseLinkedList.reverseList(head);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

    }
}
