package cn.lcx.design.pattern.ratelimiter.rule;

/**
 * @author： lichenxu
 * @date： 2024/12/2518:32
 * @description：
 * @version： v1.0
 */
public class TrieRateLimitRule implements RateLimitRule {


    public TrieRateLimitRule(RuleConfig ruleConfig) {
        //...
    }

    public ApiLimit getLimit(String appId, String api) {
        //...
    }
}
