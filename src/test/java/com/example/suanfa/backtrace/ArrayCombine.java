package com.example.suanfa.backtrace;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author: luozijian
 * @date: 8/4/21 08:44:27
 * @description:
 * https://leetcode-cn.com/problems/combinations/submissions/
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 *
 * 重点概括：
 *
 * 如果解决一个问题有多个步骤，每一个步骤有多种方法，题目又要我们找出所有的方法，可以使用回溯算法；
 * 回溯算法是在一棵树上的 深度优先遍历（因为要找所有的解，所以需要遍历）；
 * 组合问题，相对于排列问题而言，不计较一个组合内元素的顺序性（即 [1, 2, 3] 与 [1, 3, 2] 认为是同一个组合），
 * 因此很多时候需要按某种顺序展开搜索，这样才能做到不重不漏。
 *
 * 链接：https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
 *
 */
public class ArrayCombine {

    @Test
    public void testCombine(){
        System.out.println(combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> res = new ArrayList<>();
        if(k <= 0 || n < k){
            return res;
        }
        Deque<Integer> path = new ArrayDeque();
        dfs(n, k, 1, path, res);
        return res;
    }


    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res){
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = begin; i <= n; i++){
            path.addLast(i);
            System.out.println("i=" + i +",递归之前 => " + path);
            dfs(n, k, i + 1, path, res);
            path.removeLast();
            System.out.println("i=" + i +",递归之后 => " + path);
        }
    }





}