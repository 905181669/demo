package com.example.suanfa.list;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: luozijian
 * @date: 6/28/21 20:22:02
 * @description:
 */
public class ListNodeTest {

    public ListNode head = new ListNode(0);


    @Before
    public void buildHead(){

        ListNode cur = head;
        int size = 5;
        for(int i = 1; i <= size; i++){
            cur.next = new ListNode(i);
            cur = cur.next;
        }

        head = head.next;
    }

    @Test
    public void testDoublePointReverse(){
        print("反转前：", head);
        ListNode newHead = doublePointReverse1(head);
        print("反转后：", newHead);
    }

    /**
     * 双指针法 反转链表
     * @param head
     * @return
     */
    public ListNode doublePointReverse(ListNode head){

        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            // step1：记录当前节点的下一个节点
            ListNode next = cur.next;
            // step2: 设置当前节点的下一个指针指向pre
            cur.next = pre;

            // step3: 将当前节点复制给上一个节点，然后将下一个节点复制当前节点，为下一个循环做准备
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode doublePointReverse1(ListNode head){

        if(head == null) {
            return head;
        }

        ListNode cur = head.next;
        ListNode pre = head;
        pre.next = null;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
    }



    @Test
    public void reverseList1(){


        ListNode newHead = reverseList1(head);

        print("", newHead);
    }

    /**
     * 用递归反转链表
     * 弹出时反转
     * @param head
     * @return
     *
     *
     * head代表当前层
     */
    public  ListNode reverseList1(ListNode head) {
        //head.next == null 说明当前head是最后一个
        if(head == null || head.next == null){
            return head;
        }

        /**
         * 递时做事
         */
//        System.out.println(head.val);
        ListNode newHead = reverseList1(head.next);
        System.out.println(newHead.val);

        /**
         * 归时做事，利用层的概念保存记录
         * 而动态规划则是利用dp数组保存记录
         * head.next是最后一个，逐一将最后一个的next指针指向前一个node
         */
        head.next.next = head;
        head.next = null;

        return newHead;

    }


    @Test
    public void test(){

        print("", reverseUseDigui(head));
    }

    private ListNode reverseUseDigui(ListNode head){
        // 如果一开始head就为null，直接返回
        if(head == null || head.next == null){
            return head;
        }

        ListNode newHead = reverseUseDigui(head.next);

        return newHead;
    }


    /**
     * 另一版本的递归
     * @param prev
     * @param cur
     * @return
     */
    private ListNode reverse(ListNode prev, ListNode cur) {
        if(cur == null){
            return prev;
        }

        ListNode temp  = cur.next;
        cur.next = prev;
        prev = cur;
        cur = temp;

        return reverse(prev, cur);

    }





    @Test
    public void testRemoveElements(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        removeElements(head, 2);
    }

    public static ListNode removeElements(ListNode head, int val) {
        if(head==null)
            return null;
        ListNode res = removeElements(head.next,val);

        //先赋值，后决定赋什么值，
        head.next = res;
        if(head.val==val){
            return head.next;
        }else{
            return head;
        }
    }


    @Test
    public void testDfs(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode node = dfs(head);
        System.out.println("head: " + node.val);
    }


    /**
     * 倒序打印链表
     * @param head
     * @return
     */
    public static ListNode dfs(ListNode head){

        if(head == null){
            return head;
        }

        /**
         * 关键点：dfs返回的是下一层的内容, 那就是说res指向下一层的返回
         * 那上一层应该用head.next指向它
         */
        ListNode res = dfs(head.next);

        /**
         * 当前层的next引用指向res
         */
        head.next = res;
        System.out.println(res);
        System.out.println(head.val);

        /**
         * 返回这一层的head给上一层
         */
        return head;
    }



    @Test
    public void testIsPalindrome(){
        System.out.println(isPalindrome(head));
    }


    public boolean isPalindrome(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null){
            slow = slow.next;
        }

        /**
         * 反转slow后面的节点
         */
        ListNode newHead = null;
        while(slow != null){
            ListNode temp = slow.next;
            slow.next = newHead;

            newHead = slow;

            slow = temp;
        }

        slow = newHead;

        fast = head;
        while(fast != null && slow != null){
            if(fast.val != slow.val){
                return false;
            }

            fast = fast.next;
            slow = slow.next;
        }

        return true;

    }


    @Test
    public void testRemove(){

        print("", head.next);
        ListNode node = remove(head, 10);
        System.out.println(node.val);
        print("", head.next);
    }

    /**
     * 删除节点=val的node
     * @param head
     * @param val
     * @return
     */
    public  ListNode remove(ListNode head, int val){

        ListNode pre = head;
        while(head != null){

            if(head.val == val){
                pre.next = head.next;

                return head;
            }
            pre = head;
            head = head.next;

        }

        return null;

    }

    public void print(String label, ListNode head){

        List list = Lists.newArrayList();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        String result = Joiner.on("->").join(list);
        System.out.println(label + ": " + result);

    }




    @Test
    public void testSwapPairs(){

//        ListNode newHead = swapPairs(head.next);
//        print(newHead);

        print("", head);
        ListNode newHead = swapPairs1(head);
        print("", newHead);

    }


    public ListNode swapPairs(ListNode head) {
        // base case 退出提交
        if(head == null || head.next == null) return head;
        // 获取当前节点的下一个节点
        ListNode next = head.next;
        // 进行递归
        ListNode newNode = swapPairs(next.next);
        // 这里进行交换
        next.next = head;
        head.next = newNode;

        return next;
    }


    /**
     * 虚拟头结点
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {

        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode prev = dummyNode;

        while (prev.next != null && prev.next.next != null) {
            ListNode temp = head.next.next; // 缓存 next
            prev.next = head.next;          // 将 prev 的 next 改为 head 的 next
            head.next.next = head;          // 将 head.next(prev.next) 的next，指向 head
            head.next = temp;               // 将head 的 next 接上缓存的temp
            prev = head;                    // 步进1位
            head = head.next;               // 步进1位

        }
        return dummyNode.next;
    }







}