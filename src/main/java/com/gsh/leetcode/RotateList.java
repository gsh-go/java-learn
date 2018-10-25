package com.gsh.leetcode;

/**
 * 61、旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * @Author: gsh
 * @Date: Created in 2018/10/24 8:09
 */


public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head;
        ListNode temp = head;
        int length = 0;

        while (temp != null) {
            temp = temp.next;
            length++;
        }
        if (k >= length) {
            k = k % length;
        }
        if (k == 0) {
            return head;
        }

        int step = length - k;
        temp = head;
        for (int i = 0; i < step - 1; i++) {
            temp = temp.next;
        }
        newHead = temp.next;
        temp.next = null;

        temp = newHead;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;

        return newHead;
    }
}

