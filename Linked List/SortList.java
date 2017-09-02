package interview.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 链表排序
 * 4->3->2
 * 2->3->4
 */
public class SortList {
    public static void main(String[] args) {
    }

    public static ListNode sortList(ListNode head) {
        if (head == null)
            return null;

        List<Integer> list = new ArrayList<>();
        while (head != null)
        {
            list.add(head.val);
            head = head.next;
        }

        Collections.sort(list);

        ListNode temp = null;
        ListNode newNode = null;
        for (int i : list)
        {
            if (temp == null)
            {
                temp = new ListNode(i);
                newNode = temp;
                continue;
            }

            temp.next = new ListNode(i);
            temp = temp.next;
        }
        return newNode;
    }


    static class ListNode
    {
        int val;
        ListNode(int value)
        {
            this.val = value;
            next = null;
        }
        ListNode next;
    }
}
