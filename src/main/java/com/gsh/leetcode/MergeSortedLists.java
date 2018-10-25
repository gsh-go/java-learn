package com.gsh.leetcode;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @Author: gsh
 * @Date: Created in 2018/10/25 9:04
 * @Description:
 */
public class MergeSortedLists {

    /**
     * 合并两个有序列表
     *
     * @param list_1
     * @param list_2
     * @return
     */
    public ListNode mergeLists(ListNode list_1, ListNode list_2) {
        if (list_1 == null)
            return list_2;
        if (list_2 == null)
            return list_1;

        ListNode head = new ListNode(0);
        ListNode temp = head;
        while (list_1 != null && list_2 != null) {
            if (list_1.val < list_2.val) {
                temp.next = list_1;
                list_1 = list_1.next;
            } else {
                temp.next = list_2;
                list_2 = list_2.next;
            }
            temp = temp.next;
        }
        if (list_1 == null)
            temp.next = list_2;
        if (list_2 == null)
            temp.next = list_1;
        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        int k = lists.length;
        while (k > 1) {
            int n = (k + 1) / 2;
            for (int i = 0; i < k / 2; i++) {
                lists[i] = mergeLists(lists[i], lists[i + n]);
            }
            k = n;
        }
        return lists[0];
    }
}
