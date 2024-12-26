package cn.lcx.design.pattern.ratelimiter.parser;

import cn.lcx.design.pattern.ratelimiter.rule.RuleConfig;

import java.io.InputStream;

/**
 * @author： lichenxu
 * @date： 2024/12/2518:30
 * @description：
 * @version： v1.0
 */
public interface RuleConfigParser {
    RuleConfig parse(InputStream in);
}
