package com.example.datastruct;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 7/24/21 09:58:06
 * @description:
 */
public class QueueTest {

    public static void main(String[] args) throws Exception{

        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
//        queue.offer("a");
//        queue.offer("b");
//        queue.offer("c", 5, TimeUnit.SECONDS);

//        MyArrayQueue myArrayQueue = new MyArrayQueue(2);
//        myArrayQueue.offer("a");
//        myArrayQueue.offer("b");
//        myArrayQueue.offer("c");
//        System.out.println(myArrayQueue.poll());
//        System.out.println(myArrayQueue.poll());
//        System.out.println(myArrayQueue.poll());

//        MyLinkedQueue myLinkedQueue = new MyLinkedQueue();
//        myLinkedQueue.offer("a");
//        myLinkedQueue.offer("b");
//        myLinkedQueue.offer("c");
//
//        System.out.println(myLinkedQueue.poll());
//        System.out.println(myLinkedQueue.poll());
//        System.out.println(myLinkedQueue.poll());




    }


    /**
     * 循环队列
     * 熟练实现一个循环队列，重点是掌握队列的判空和判满条件
     */
    public static class MyArrayQueue{

        private Object[] elementData;

        private int count;

        private int putIndex;

        private int takeIndex;

        public MyArrayQueue(int size){
            elementData = new Object[size];
        }

        /**
         * 非阻塞的入队出队操作
         * @return
         */
        public boolean offer(Object item){

            if(count == elementData.length){
                return false;
            }else{
                enqueue(item);
                return true;
            }

        }


        private void enqueue(Object item){

            elementData[putIndex] = item;
            if(++putIndex == elementData.length){
                putIndex = 0;
            }

            count++;
        }

        public Object poll(){

            if(count == 0){
                return null;
            }
            return dequeue();
        }

        private Object dequeue(){
            Object item = elementData[takeIndex];

            elementData[takeIndex] = null;
            if(++takeIndex == elementData.length){
                takeIndex = 0;
            }
            count--;
            return item;
        }

    }


    public static class MyLinkedQueue{

        private static class Node{
            private Object val;
            private Node next;

            private Node(Object val){
                this.val = val;
            }
        }

        private Node head;
        private Node tail;

        private int count;

        public MyLinkedQueue(){
            head = tail = new Node(null);
        }

        public boolean offer(Object val){
            Node node = new Node(val);

            tail.next = node;
            tail = node;
            count++;
            return true;
        }

        public Object poll(){
            if(count == 0){
                return null;
            }

            Node node = head.next;
            head.next = node.next;
            count--;
            return node.val;
        }
    }

}