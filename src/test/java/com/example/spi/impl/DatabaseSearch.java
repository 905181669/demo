package com.example.spi.impl;

/**
 * @author: luozijian
 * @date: 8/18/21 16:30:53
 * @description:
 */
import com.example.spi.Search;

import java.util.List;

public class DatabaseSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("数据搜索 "+keyword);
        return null;
    }
}