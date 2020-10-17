package com.example.demo;

/**
 * @author: luozijian
 * @date: 2020-04-21 11:19:55
 * @description:
 */
public class Function {

    private void none(int id, Ticket ticket, String admin){
        System.out.println(id);
        System.out.println(ticket);
        System.out.println(admin);
    }

    static {
        System.out.println("初始化Function");
    }
}
