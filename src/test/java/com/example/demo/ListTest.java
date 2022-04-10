package com.example.demo;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author: luozijian
 * @date: 2021/12/15 09:21:31
 * @description:
 */
public class ListTest {

    @org.junit.Test
    public void test1(){

        List<String> listA = Lists.newArrayList("1", "2", "3", "4"); //数据库
        List<String> listB = Lists.newArrayList( "6"); //参数


        //2、交集 intersection
        List<String> intersection = (List<String>) org.apache.commons.collections.CollectionUtils.intersection(listA, listB);
        System.out.println("交集：" + intersection);
        //输出:[3, 4]

        listA.removeAll(intersection);
        System.out.println("delete: " + listA);


        listB.removeAll(intersection);
        System.out.println("add: " + listB);
    }

    @org.junit.Test
    public void test2(){
        List<File1> file1List = Lists.newArrayList();
        file1List.add(File1.builder().hash("1").build());
        file1List.add(File1.builder().hash("2").build());
        file1List.add(File1.builder().hash("3").build());

        List<File1> file2List = Lists.newArrayList();
        file2List.add(File1.builder().hash("1").build());
        file2List.add(File1.builder().hash("2").build());

        System.out.println(org.apache.commons.collections.CollectionUtils.intersection(file1List, file2List));
    }


    @Test
    public void ArrayListTest() {
        int num = 100000;
        ArrayList<String> list = new ArrayList<String>(num);
        int i = 0;

        long timeStart = System.currentTimeMillis();

        while (i < num) {
            list.add(0, i + "沉默王二");
            i++;
        }
        long timeEnd = System.currentTimeMillis();

        System.out.println("ArrayList从集合头部位置新增元素花费的时间" + (timeEnd - timeStart));

    }

    /**
     * @author 微信搜「沉默王二」，回复关键字 PDF
     */
    @Test
    public  void addFromHeaderTest() {
        int num = 100000;
        LinkedList<String> list = new LinkedList<String>();
        int i = 0;
        long timeStart = System.currentTimeMillis();
        while (i < num) {
            list.addFirst(i + "沉默王二");
            i++;
        }
        long timeEnd = System.currentTimeMillis();

        System.out.println("LinkedList从集合头部位置新增元素花费的时间" + (timeEnd - timeStart));
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class File1{
    private String hash;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File1 file1 = (File1) o;
        return hash.equals(file1.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class File2{
    private String hash;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File2 file2 = (File2) o;
        return hash.equals(file2.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}