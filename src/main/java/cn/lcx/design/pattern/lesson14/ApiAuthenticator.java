package cn.lcx.design.pattern.lesson14;

/**
 * @author： lichenxu
 * @date： 2024/11/1815:48
 * @description： 鉴权接口
 * @version： v1.0
 */
public interface ApiAuthenticator {
    void auth(String url);

    void auth(ApiRequest apiRequest);
}
