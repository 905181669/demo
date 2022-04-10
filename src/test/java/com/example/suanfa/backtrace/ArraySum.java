package com.example.suanfa.backtrace;

import io.swagger.models.auth.In;
import org.junit.Test;

import java.util.*;

/**
 * @author: luozijian
 * @date: 8/8/21 09:30:40
 * @description:
 *
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 *
 * 本题就是在[1,2,3,4,5,6,7,8,9]这个集合中找到和为n的k个数的组合。
 * 回溯法
 *
 */
public class ArraySum {

    @Test
    public void test(){

        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();

        backtrace(4, 2, 1, path, res);
        System.out.println(res);
    }


    private void backtrace(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res){

        if(path.size() == k){
            //当size == k时，判断path中的和是否等于n，是则加入到res结果集中
            int sum = 0;
            Iterator<Integer> it = path.iterator();
            while(it.hasNext()){
                sum += it.next();
            }
            if(sum == n){
                res.add(new ArrayList<>(path));
                return;
            }
        }else if(path.size() > k){
            //当size>k时，继续递归下去也没用，直接返回
            return;
        }
        //只有当size<k时才需要继续递归下去


        for(int i = begin; i <= 9; i++){
            path.addLast(i);
            backtrace(n, k, i + 1, path, res);
            path.removeLast();
        }

    }



}