package com.example.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.awt.datatransfer.FlavorEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: luozijian
 * @date: 2021/11/4 20:07:15
 * @description:
 */
public class SentinelTest1 {

    public static void main(String[] args) {
        // 配置规则
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("gateway"); // 设置资源
        // QPS 不得超出 1
        rule.setCount(1);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

        // 运行被限流作用域保护的代码
        while (true) {
            if (SphO.entry("gateway")) {
                try {
                    System.out.println("hello world");
                } finally {
                    SphO.exit();
                }
            } else {
                System.out.println("blocked");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
}