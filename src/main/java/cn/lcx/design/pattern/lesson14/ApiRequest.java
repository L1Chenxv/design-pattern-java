package cn.lcx.design.pattern.lesson14;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author： lichenxu
 * @date： 2024/11/1815:47
 * @description：
 * @version： v1.0
 */
@Data
public class ApiRequest {

    private String baseUrl;
    private String appID;
    private long timeStamp;
    private String token;

    public ApiRequest(String fullUrl) {

        Map<String, String> params = parse(fullUrl);
        // TODO
        String baseUrl = "";
        String appID = "";
        long timeStamp = 0L;
        String token = "";
    }

    public static ApiRequest genFakeReq(String fullUrl) {
        ApiRequest req = new ApiRequest("geekbang", "designpattern", System.currentTimeMillis(),
                "IXIGIpJ9hdOBCyjStaDJ5Nom07g=");
        return req;
    }

    private static Map<String, String> parse(String fullUrl) {
        Map<String, String> params = new TreeMap<>();
        // TODO
        return params;
    }


    public ApiRequest(String baseUrl, String appID, long timeStamp, String token) {
        this.baseUrl = baseUrl;
        this.appID = appID;
        this.timeStamp = timeStamp;
        this.token = token;
    }

}
