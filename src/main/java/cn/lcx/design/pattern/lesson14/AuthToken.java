package cn.lcx.design.pattern.lesson14;

import lombok.Data;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 * @author： lichenxu
 * @date： 2024/11/1811:54
 * @description： token类
 * @version： v1.0
 */
@Data
public class AuthToken {

    private static final long EXPIRE_INTERVAL = 10 * 60 * 1000L;
    private long createTime;
    private String token;
    private String originalUrl;

    public AuthToken(String originalUrl, long createTime, String token) {
        this.originalUrl = originalUrl;
        this.createTime = createTime;
        this.token = token;
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public boolean isExpired() {
        if (System.currentTimeMillis() - createTime > EXPIRE_INTERVAL) {
            return true;
        }
        return false;
    }

    public boolean match(AuthToken authToken) {
        if (Objects.equals(this.token, authToken.token)) {
            return true;
        }
        return false;
    }

    public static AuthToken buildAuthToken(ApiRequest req, String password) {
        String srcStr = "";
        srcStr = "geekbang?"
                + "AppID=designpattern"
                + "&Timestamp=1465185768";

        String token = generateToken(srcStr, password);
        System.out.println("buildAuthToken: " + token);
        AuthToken authToken = new AuthToken(token);
        return authToken;
    }

    private static String generateToken(String value, String key) {
        return hmacSHA256(value, key);
    }

    private static String hmacSHA256(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes("UTF-8");
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes("UTF-8"));

            byte[] result = Base64.getEncoder().encode(rawHmac);

            //  Covert array of Hex bytes to a String
            return new String(result, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
