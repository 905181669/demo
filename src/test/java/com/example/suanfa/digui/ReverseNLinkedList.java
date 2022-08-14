package com.example.suanfa.digui;

import com.example.suanfa.list.ListNode;
import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2022/7/28
 * @description:
 */
public class ReverseNLinkedList {

    // 后驱节点
    ListNode successor = null;
    /**
     * 反转链表前N个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode reverseN(ListNode head, int n) {
        if(n == 1) {
            // 记录第n+1个节点
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n-1);
        head.next.next = head;
        head.next = successor;
        return last;
    }


    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == 1) {
            return reverseN(head, n);
        }

        head.next = reverseBetween(head.next, m - 1, n - 1);
        System.out.println(head.val);
        return head;
    }


    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode newHead = reverseBetween(head, 3, 4);
//        System.out.println(newHead.val);
    }
}