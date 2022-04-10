package com.example.suanfa.dynamicProgram;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 6/30/21 18:27:19
 * @description:
 */
public class MaxScoreSightseeingPair {


    @Test
    public void test(){

        int [] value = {8,1,5,2,6};
        System.out.println(maxScoreSightseeingPair(value));
    }

    /**
     * 求values[i] + values[j] + i - j的最大值
     * @param values
     * @return
     */
    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0, mx = values[0] + 0;
        for (int j = 1; j < values.length; ++j) {
            ans = Math.max(ans, mx + values[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }

}