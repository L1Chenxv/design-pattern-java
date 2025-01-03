package cn.lcx.design.pattern.ratelimiter.datasource;

import cn.lcx.design.pattern.ratelimiter.parser.JsonRuleConfigParser;
import cn.lcx.design.pattern.ratelimiter.parser.RuleConfigParser;
import cn.lcx.design.pattern.ratelimiter.parser.YamlRuleConfigParser;
import cn.lcx.design.pattern.ratelimiter.rule.RuleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author： lichenxu
 * @date： 2024/12/2518:31
 * @description：
 * @version： v1.0
 */
public class FileRuleConfigSource implements RuleConfigSource {

    private static final Logger log = LoggerFactory.getLogger(FileRuleConfigSource.class);
    public static final String API_LIMIT_CONFIG_NAME = "ratelimiter-rule";
    public static final String YAML_EXTENSION = "yaml";
    public static final String YML_EXTENSION = "yml";
    public static final String JSON_EXTENSION = "json";

    private static final String[] SUPPORT_EXTENSIONS = new String[]{YAML_EXTENSION, YML_EXTENSION, JSON_EXTENSION};
    private static final Map<String, RuleConfigParser> PARSER_MAP = new HashMap<>();

    static {
        PARSER_MAP.put(YAML_EXTENSION, new YamlRuleConfigParser());
        PARSER_MAP.put(YML_EXTENSION, new YamlRuleConfigParser());
        PARSER_MAP.put(JSON_EXTENSION, new JsonRuleConfigParser());
    }

    @Override
    public RuleConfig load() {
        InputStream in = null;
        for (String extension : SUPPORT_EXTENSIONS) {
            try {
                in = this.getClass().getResourceAsStream("/" + getFileNameByExt(extension));
                if (in != null) {
                    RuleConfigParser ruleConfigParser = PARSER_MAP.get(extension);
                    return ruleConfigParser.parse(in);
                }
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        log.error("close file error:{}", e);
                    }
                }
            }
        }

        return null;
    }

    private String getFileNameByExt(String extension) {
        return API_LIMIT_CONFIG_NAME + "." + extension;
    }
}
