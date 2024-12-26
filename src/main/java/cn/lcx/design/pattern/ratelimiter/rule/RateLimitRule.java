package cn.lcx.design.pattern.ratelimiter.rule;

/**
 * @author： lichenxu
 * @date： 2024/12/2517:49
 * @description：
 * @version： v1.0
 */
public interface RateLimitRule {

    ApiLimit getLimit(String appId, String api);
}
