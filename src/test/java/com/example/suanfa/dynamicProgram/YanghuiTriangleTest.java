package com.example.suanfa.dynamicProgram;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: luozijian
 * @date: 7/4/21 16:01:09
 * @description:
 */
public class YanghuiTriangleTest {


    @Test
    public void test(){
        System.out.println(getRow(3));
    }


    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;


    }
}