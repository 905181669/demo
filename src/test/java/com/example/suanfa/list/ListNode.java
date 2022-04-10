package com.example.suanfa.list;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 5/3/21 10:29:15
 * @description:
 */
public class ListNode {


    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    static void traverse(ListNode head){
        for(ListNode p = head; p != null; p = p.next){
            System.out.println(p.val);
        }
    }

    static void traverse1(ListNode head){
        //前序遍历
        if(head != null){
            System.out.println(head.val);
            traverse1(head.next);
        }
    }

    static void traverse2(ListNode head){
        //后序遍历
        if(head != null){
            traverse2(head.next);
            System.out.println(head.val);
        }
    }


    /**
     * 双指针反转链表
     * 如果有环，则反转后的头结点head==原来链表的头结点head
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {

        ListNode newHead = null;
        while(head != null){

            ListNode temp = head.next;

            head.next = newHead;

            newHead = head;

            head = temp;
        }

        return newHead;
    }






}