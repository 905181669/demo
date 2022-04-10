package com.example.suanfa.stack;

/**
 * @author: luozijian
 * @date: 6/25/21 15:33:04
 * @description:
 * 自定义顺序栈
 */
public class ArrayStack {

    private String[] items;
    private int count;
    private int n;

    public ArrayStack(int n){
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    public boolean push(String item){
        if(count == n){
            return false;
        }

        this.items[count] = item;
        count++;
        return true;
    }

    public String pop(){
        if(count == 0){
            return null;
        }

        String item = this.items[--count];

        return item;

    }

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(10);
        stack.push("a");
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }

}