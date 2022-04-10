package com.example.suanfa.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: luozijian
 * @date: 2022/3/9
 * @description:
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("abcabca"));

    }
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        Set set = new HashSet();
        int ans = 0;
        for(int i = 0; i < n; i++){
            set.add(s.charAt(i));
            for(int j = i + 1; j < n; j++) {
                if(!set.contains(s.charAt(j))){
                    set.add(s.charAt(j));
                }else{
                    ans = Math.max(set.size(), ans);
                    set.clear();
                    break;
                }
            }
        }
        return Math.max(set.size(), ans);
    }

}