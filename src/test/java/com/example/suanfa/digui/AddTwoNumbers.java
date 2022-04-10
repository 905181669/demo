package com.example.suanfa.digui;

import com.example.suanfa.list.ListNode;
import org.junit.Test;

/**
 * @author: luozijian
 * @date: 6/29/21 10:52:03
 * @description:
 */
public class AddTwoNumbers {


    @Test
    public void test(){

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);


        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode resNode = addTwoNumbers(l1, l2);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead1 = reverse(l1);
        ListNode newHead2 = reverse(l2);

        int a1 = 0;
        while(newHead1 != null){
            a1 = a1 * 10 + newHead1.val;
            newHead1 = newHead1.next;
        }

        int a2 = 0;
        while(newHead2 != null){
            a2 = a2 * 10 + newHead2.val;
            newHead2 = newHead2.next;
        }

        int a3 = a1 + a2;
        int div = a3;
        ListNode resNode = new ListNode(0);
        ListNode sumNode = resNode;
        while(div != 0){
            int tmp = div % 10;
            div = div / 10;
            resNode.next = new ListNode(tmp);
            resNode = resNode.next;
        }

        return sumNode.next;

    }

    public static ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}