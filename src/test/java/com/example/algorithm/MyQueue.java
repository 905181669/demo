package com.example.algorithm;

/**
 * @author: luozijian
 * @date: 2020-05-26 21:59:47
 * @description:
 */
public class MyQueue {

    public static void main(String[] args) throws Exception{
        MyQueue myQueue = new MyQueue(6);
        myQueue.enQueue(3);
        myQueue.enQueue(5);

        myQueue.deQueue();
        myQueue.deQueue();

        myQueue.output();


    }

    private int[] array;
    private int front;
    private int rear;

    public MyQueue(int capacity){
        this.array = new int[capacity];
    }


    public void enQueue(int ele)throws Exception{
        if((rear + 1)% array.length == front){
            throw new Exception("队列已满");
        }

        array[rear] = ele;
        rear = (rear + 1) % array.length;
    }


    public int deQueue() throws Exception{
        if(rear == front){
            throw new Exception("队列已空");
        }

        int dfeQueueEle = array[front];
        front = (front + 1)% array.length;
        return dfeQueueEle;
    }


    public void output(){
        for(int i = front; i != rear; i = (i+1) % array.length){
            System.out.println(array[i]);
        }
    }
}

