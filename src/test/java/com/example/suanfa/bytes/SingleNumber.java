package com.example.suanfa.bytes;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: luozijian
 * @date: 2022/3/7
 * @description:
 */
public class SingleNumber {

    public static void main(String[] args) {

        int[] nums = {1,2,3,1,2};
        System.out.println(singleNumber(nums));
    }

    /**
     * 思路：采用异或,数组内所有元素异或
     *
     * 归零律：a ^ a = 0
     * 恒等律：a ^ 0 = a
     * 交换律：a ^ b = b ^ a
     * 结合律：a ^b ^ c = a ^ (b ^ c) = (a ^ b) ^ c;
     * 自反：a ^ b ^ a = b.
     * d = a ^ b ^ c 可以推出 a = d ^ b ^ c.
     * 若x是二进制数0101，y是二进制数1011；则x^y=1110
     * [4,1,2,1,2] 则 4^1^2^1^2=4 故思路为该数组内所有元素异或最后的结果则为目标元素
     *
     * 作者：孙悟空
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm0u83/?discussion=Rx8VBV
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {

        int res = 0;
        for(int i : nums) {
            res ^= i;
            System.out.println(res);
        }
        return res;
    }

    public int singleNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(!set.add(num)) {
                set.remove(num);
            }
        }
        return (int)set.toArray()[0];
    }
}