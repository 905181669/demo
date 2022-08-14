package com.example.suanfa.digui;

import com.example.suanfa.list.ListNode;
import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2022/7/28
 * @description:
 */
public class ReverseLinkedList {

    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode reverse = reverse(head);
        while(reverse != null) {
            System.out.println(reverse.val);
            reverse = reverse.next;
        }
    }

    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode last = reverse(head.next); // last在归时一直没变过，所以是最后一个节点
        head.next.next = head; //回归时通过栈结构倒着反转指针
        head.next = null;
        return last;
    }


}