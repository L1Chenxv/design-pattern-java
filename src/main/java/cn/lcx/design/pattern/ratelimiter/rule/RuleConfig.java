package cn.lcx.design.pattern.ratelimiter.rule;

import java.util.List;

/**
 * @author： lichenxu
 * @date： 2024/12/2517:49
 * @description：
 * @version： v1.0
 */
public class RuleConfig {

    private List configs;

    public List<AppRuleConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List configs) {
        this.configs = configs;
    }

    public static class AppRuleConfig {
        private String appId;
        private List limits;

        public AppRuleConfig() {
        }

        public AppRuleConfig(String appId, List limits) {
            this.appId = appId;
            this.limits = limits;
        } //...省略getter、setter方法...
    }
}
