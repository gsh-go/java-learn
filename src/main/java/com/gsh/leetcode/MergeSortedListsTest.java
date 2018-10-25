package com.gsh.leetcode;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/25 9:30
 * @Description:
 */
public class MergeSortedListsTest {

    public static void main(String[] args) {
        ListNode list_1 = new ListNode(1);
        list_1.next = new ListNode(4);
        list_1.next.next = new ListNode(5);

        ListNode list_2 = new ListNode(1);
        list_2.next = new ListNode(3);
        list_2.next.next = new ListNode(4);

        ListNode list_3 = new ListNode(2);
        list_3.next = new ListNode(6);

        ListNode[] list = {list_1, list_2,list_3};

        MergeSortedLists mergeSortedLists = new MergeSortedLists();
        ListNode result = mergeSortedLists.mergeKLists(list);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
