package interview.algorithm;

/**
 * 翻转单链表
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        //测试方法一
        ListNode node = reverse(head);
        while (node != null)
        {
            System.out.println(node.val);
            node = node.next;
        }

        //测试方法二
        ListNode node1 = reverse(head);
        while (node1 != null)
        {
            System.out.println(node1.val);
            node1 = node1.next;
        }
    }

    /**
     * 方法一：逐个翻转
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if (head == null)
            return null;

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null)
        {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    //方法二：不断选择后一个结点，插入到头节点后面
    public static ListNode re(ListNode head) {
        if (head == null) return null;

        if (head.next == null)
            return head;

        if (head.next.next == null){
            ListNode n = head.next;
            head.next = null;
            n.next = head;
            return n;
        }

        ListNode cur = head.next.next;
        ListNode pre = head.next;
        while (cur != null){
            //临时结点，存放当前结点的下一个结点
            ListNode temp = cur.next;
            //将cur的前一个结点指向cur的下一个结点
            pre.next = temp;
            //解除cur.next的先前指向，重定向为head下一个结点。
            cur.next = head.next;
            //解除head的先前指向，重定向为cur
            head.next = cur;
            //调整引用，cur向后一位
            cur = temp;
        }

        //添加引用到头节点的下一个结点，即翻转链表后的新头结点。
        ListNode newNode = head.next;
        //移动头节点到尾部
        pre.next = head;
        //解除下一个指向的引用，避免死循环
        head.next = null;
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
