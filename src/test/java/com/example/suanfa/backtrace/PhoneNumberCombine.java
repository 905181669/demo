package com.example.suanfa.backtrace;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * @author: luozijian
 * @date: 8/8/21 10:04:46
 * @description:
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 *
 */
public class PhoneNumberCombine {

    @Test
    public void test(){

        System.out.println(letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {

        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        StringBuilder path = new StringBuilder();
        List<String> res = Lists.newArrayList();
        backtrack(digits, 0, phoneMap, path, res);
        return res;
    }

    public void backtrack(String digits, int begin, Map<Character, String> phoneMap, StringBuilder path, List<String> res) {

        if(path.length() == digits.length()){
            res.add(path.toString());
            return;
        }

        char digit = digits.charAt(begin);
        String letters = phoneMap.get(digit);

        for(int i = 0; i < letters.length(); i++){

            path.append(letters.charAt(i));
            backtrack(digits, begin+1, phoneMap, path, res);
            path.deleteCharAt(begin);
        }
    }

}