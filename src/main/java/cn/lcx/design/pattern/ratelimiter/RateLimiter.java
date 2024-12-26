package cn.lcx.design.pattern.ratelimiter;

import cn.lcx.design.pattern.ratelimiter.alg.RateLimitAlg;
import cn.lcx.design.pattern.ratelimiter.datasource.FileRuleConfigSource;
import cn.lcx.design.pattern.ratelimiter.datasource.RuleConfigSource;
import cn.lcx.design.pattern.ratelimiter.rule.ApiLimit;
import cn.lcx.design.pattern.ratelimiter.rule.RateLimitRule;
import cn.lcx.design.pattern.ratelimiter.rule.RuleConfig;
import cn.lcx.design.pattern.ratelimiter.rule.TrieRateLimitRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author： lichenxu
 * @date： 2024/12/2517:48
 * @description：
 * @version： v1.0
 */
public class RateLimiter {
    private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);
    // 为每个api在内存中存储限流计数器
    private ConcurrentHashMap<String, RateLimitAlg> counters = new ConcurrentHashMap<>();
    private RateLimitRule rule;

    public RateLimiter() {
        RuleConfigSource configSource = new FileRuleConfigSource();
        RuleConfig ruleConfig = configSource.load();
        this.rule = new TrieRateLimitRule(ruleConfig);
    }

    public boolean limit(String appId, String url) {
        ApiLimit apiLimit = rule.getLimit(appId, url);
        if (apiLimit == null) {
            return true;
        } // 获取api对应在内存中的限流计数器（rateLimitCounter）
        String counterKey = appId + ":" + apiLimit.getApi();
        RateLimitAlg rateLimitCounter = counters.get(counterKey);
        if (rateLimitCounter == null) {
            RateLimitAlg newRateLimitCounter = new RateLimitAlg(apiLimit.getLimit());
            rateLimitCounter = counters.putIfAbsent(counterKey, newRateLimitCounter);
            if (rateLimitCounter == null) {
                rateLimitCounter = newRateLimitCounter;
            }
        } // 判断是否限流
        return rateLimitCounter.tryAcquire();
    }
}
