package com.yundun.sdk;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;

class SignRequest {

    private static Gson gson = new Gson();

    //生成签名
    static String make(Map<String, Object> params, String appSecret) {
        params.put("algorithm", "HMAC-SHA256");
        params.put("issued_at", System.currentTimeMillis() / 1000);
        String encodedPayload = base64UrlEncode(gson.toJson(params).getBytes());
        byte[] hashedSig = null;
        try {
            hashedSig = hashSignature(encodedPayload, appSecret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encodedSig = base64UrlEncode(hashedSig);
        return encodedSig + "." + encodedPayload;
    }

    private static String base64UrlEncode(byte[] bs) {
        return new String(Base64.encodeBase64(bs)).replace("+", "-").replace("/", "_");
    }

    private static byte[] hashSignature(String encodedData, String appSecret) throws Exception {
        if (appSecret == null) {
            throw new IllegalArgumentException("Secret key is null");
        }
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(appSecret.getBytes(), "HmacSHA256");
            mac.init(secret);
            return mac.doFinal(encodedData.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
