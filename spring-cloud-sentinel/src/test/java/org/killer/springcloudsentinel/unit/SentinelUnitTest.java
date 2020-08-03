package org.killer.springcloudsentinel.unit;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;

/**
 * @author wqs
 * @date 2020/8/3-14:30
 * @description
 */
public class SentinelUnitTest {

    public static void main(String[] args) {
        ArrayList<FlowRule> rules = new ArrayList<>();
        // flowrule 流量规则，，
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("hello");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(20);

        rules.add(flowRule);

        FlowRuleManager.loadRules(rules);
    }

}
