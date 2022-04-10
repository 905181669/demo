package com.example.suanfa.redis;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author: luozijian
 * @date: 7/10/21 10:48:02
 * @description:
 */
public class LRUCache {


    public static void main(String[] args) {

        LRUCache cache = new LRUCache(3);

        cache.set("a", 1);

        System.out.println(cache.get("a"));



    }


    private HashMap<String, DLinkedNode>
            cache = new HashMap<>();

    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity){
        head = new DLinkedNode();
        tail = new DLinkedNode();

        head.pre = null;
        head.post = tail;

        tail .pre = head;
        tail.post = null;

        this.capacity = capacity;
    }


    public Integer get(String k){
        DLinkedNode node = cache.get(k);
        if(node == null){
            return null;
        }

        moveToHead(node);
        return node.value;
    }


    public void set(String k, Integer v){

        DLinkedNode node = cache.get(k);
        if(node == null){
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = k;
            newNode.value = v;

            cache.put(k, newNode);
            addNode(newNode);
            count++;

            if(count > capacity){
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                count--;
            }

        }else{
            node.value = v;
            moveToHead(node);
        }
    }



    private void removeNode(DLinkedNode node){

        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;

        //因为有head和tail这两个哨兵节点，这里可以安全的删除
        pre.post = post;
        post.pre = pre;
    }

    /**
     * 只加在头
     * @param node
     */
    private void addNode(DLinkedNode node){
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    private DLinkedNode popTail(){

        DLinkedNode res = tail.pre;
        removeNode(res);
        return res;

    }


    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addNode(node);
    }
}




class DLinkedNode {
    String key;
    int value;
    DLinkedNode pre;
    DLinkedNode post;
}