package com.example.datastruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: luozijian
 * @date: 7/22/21 09:01:38
 * @description:
 */
public class StackTest {

    public static void main(String[] args) {

        Stack stack = new Stack();
        System.out.println(stack.capacity());
//        stack.setSize(21);
        System.out.println(stack.capacity());

        stack.push(1);
        System.out.println(stack.pop());
//        System.out.println(stack.pop());


        MyArrayStack myStack = new MyArrayStack();
        myStack.push(2);
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());


        MyLinkedStack myLinkedStack = new MyLinkedStack();
        myLinkedStack.push("hello");
        myLinkedStack.push("world");

        System.out.println(myLinkedStack.peek());

        System.out.println(myLinkedStack.pop());
        System.out.println(myLinkedStack.pop());


        System.out.println(isValid("()"));


    }


    /**
     * 判断括号有效性
     * https://leetcode-cn.com/problems/valid-parentheses/solution/you-xiao-de-gua-hao-by-leetcode-solution/
     * @param s
     * @return
     */
    public static boolean isValid(String s){

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        //单数肯定不是有效的
        if(s.length() % 2 == 1){
            return false;
        }

        Stack stack = new Stack();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(pairs.containsKey(c)){

                if(stack.isEmpty() || stack.peek() != pairs.get(c)){
                    return false;
                }

                stack.pop();
            }else{
                stack.push(c);
            }

        }

        return stack.isEmpty();

    }


    public static class MyArrayStack{

        protected Object[] elementData;

        protected int elementCount;

        public MyArrayStack(){
            elementData = new Object[10];
        }


        public Object push(Object item){

            elementData[elementCount++] = item;
            return item;
        }


        public Object peek(){
            return elementData[elementCount - 1];
        }


        public Object pop(){
            Object item = peek();
            elementData[elementCount - 1] = null;
            elementCount--;
            return item;
        }

    }


    public static class MyLinkedStack{

        private static class Node{
            public Object val;
            public Node next;

            public Node(Object val){
                this.val = val;
            }
        }


        protected int elementCount;

        protected Node head;

        protected Node tail;

        public MyLinkedStack(){
            /**
             * 初始化时头尾指针指向一个空Node
             */
            head = tail = new Node(null);
        }


        protected Object push(Object item){
            Node node = new Node(item);
            tail.next = node; //尾插法

            tail = node;
            elementCount++;
            return item;
        }

        protected Object peek(){

            if(elementCount == 0){
                return null;
            }

            return tail.val; //直接返回尾节点即可
        }

        protected Object pop(){
            Object item = peek();
            if(elementCount == 0){
                return null;
            }

            Node cur = head;
            while(cur.next != tail){
                cur = cur.next;
            }
            cur.next = null;
            tail = cur; //设置倒数第二个节点为尾节点
            elementCount--;

            return item;
        }


    }


}