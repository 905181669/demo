package com.example.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: luozijian
 * @date: 1/25/21 17:59:58
 * @description:
 */
public class ListTest {

    public static void main(String[] args) {

        List list = new ArrayList<>(1);
        System.out.println(list.size());

        Iterator it = list.iterator();
        while(it.hasNext()) {
            it.remove();
        }

    }

}
