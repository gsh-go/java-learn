package com.gsh.leetcode;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/24 9:08
 * @Description:
 */
public class RotateListTest {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
      /*  head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);*/
        RotateList rotateList = new RotateList();
        ListNode result = rotateList.rotateRight(head, 2);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }


    }
}
