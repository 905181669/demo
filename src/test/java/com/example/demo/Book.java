package com.example.demo;

import java.util.Date;
import java.util.List;

/**
 * @author: luozijian
 * @date: 2020-06-20 12:58:34
 * @description:
 */
public class Book {

    public Book() {
        System.out.println("创建book");
    }

    public static void main(String[] args) {
        Book noteBook = new NoteBook();
    }
}


class NoteBook extends Book{

    public NoteBook() {
        System.out.println("创建noteBook");
    }
}