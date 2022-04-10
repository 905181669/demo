package com.example.spi.test;

/**
 * @author: luozijian
 * @date: 8/18/21 16:33:17
 * @description:
 */
import com.example.spi.Search;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TestCase {
    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search =  iterator.next();
            search.searchDoc("hello world");
        }
    }
}