package cn.lcx.design.pattern.ratelimiter.datasource;

import cn.lcx.design.pattern.ratelimiter.rule.RuleConfig;

/**
 * @author： lichenxu
 * @date： 2024/12/2518:31
 * @description：
 * @version： v1.0
 */
public interface RuleConfigSource {
    RuleConfig load();
}
