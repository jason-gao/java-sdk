package com.yundun.sdk;

import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class YundunSDK {

    // 线上需要改动该配置
    private static final String Base_API_URL = "http://apiv4.yundun.com/V4/";

    private String app_id; //必需
    private String app_secret; //必需
    private Integer user_id;
    private String client_ip;
    private String client_userAgent;

    public YundunSDK(String app_id, String app_secret) {
        this.app_id = app_id;
        this.app_secret = app_secret;
    }

    public YundunSDK(String app_id, String app_secret, Integer user_id, String client_ip, String client_userAgent) {
        this(app_id, app_secret);
        this.user_id = user_id;
        this.client_ip = client_ip;
        this.client_userAgent = client_userAgent;
    }

    private String signParams(Map<String, Object> params) {
        params.putIfAbsent("user_id", user_id);
        params.putIfAbsent("client_ip", client_ip);
        params.putIfAbsent("client_userAgent", client_userAgent);
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("body", params);
        return SignRequest.make(bodyMap, app_secret);
    }


    private String buildUrl(String apiUrl) {
        if (apiUrl == null || "".equals(apiUrl)) {
            throw new IllegalArgumentException("请求URL不能为空");
        }
        if (apiUrl.contains("http://") || (apiUrl.contains("https://"))) {
            return apiUrl;
        } else {
            return Base_API_URL + apiUrl;
        }
    }

    /**
     * @param apiUrl 接口url 可以使用两种形式（1）接口名：Domain.Record.Type （2）完整url：http://api.yundun.cn/V1/Domain.Record.Type
     */
    public String get(String apiUrl) {
        String url = buildUrl(apiUrl);
        String res = "";
        try {
            res = Request.Get(url)
                    .addHeader("X-Auth-App-Id", app_id)
                    .execute()
                    .returnContent().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param apiUrl 接口url 可以使用两种形式（1）接口名：Domain.Record.Type （2）完整url：http://api.yundun.cn/V1/Domain.Record.Type
     * @param params 接口参数
     */
    public String post(String apiUrl, Map<String, Object> params) {
        String url = buildUrl(apiUrl);
        String appid = (String) (params.containsValue("app_id") ? params.get("app_id") : app_id);
        String res = "";
        try {
            res = Request.Post(url)
                    .addHeader("X-Auth-Sign", signParams(params))
                    .addHeader("X-Auth-App-Id", appid)
                    .execute()
                    .returnContent()
                    .toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param apiUrl 接口url 可以使用两种形式（1）接口名：Domain.Record.Type （2）完整url：http://api.yundun.cn/V1/Domain.Record.Type
     * @param params 接口参数
     */
    public String put(String apiUrl, Map<String, Object> params) {
        String url = buildUrl(apiUrl);
        String appid = (String) (params.containsValue("app_id") ? params.get("app_id") : app_id);
        String res = "";
        try {
            res = Request.Put(url)
                    .addHeader("X-Auth-Sign", signParams(params))
                    .addHeader("X-Auth-App-Id", appid)
                    .execute()
                    .returnContent()
                    .toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param apiUrl 接口url 可以使用两种形式（1）接口名：Domain.Record.Type （2）完整url：http://api.yundun.cn/V1/Domain.Record.Type
     * @param params 接口参数
     */
    public String delete(String apiUrl, Map<String, Object> params) {
        String url = buildUrl(apiUrl);
        String appid = (String) (params.containsValue("app_id") ? params.get("app_id") : app_id);
        String res = "";
        try {
            res = Request.Delete(url)
                    .addHeader("X-Auth-Sign", signParams(params))
                    .addHeader("X-Auth-App-Id", appid)
                    .execute()
                    .returnContent()
                    .toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @param apiUrl 接口url 可以使用两种形式（1）接口名：Domain.Record.Type （2）完整url：http://api.yundun.cn/V1/Domain.Record.Type
     * @param params 接口参数
     */
    public String patch(String apiUrl, Map<String, Object> params) {
        String url = buildUrl(apiUrl);
        String appid = (String) (params.containsValue("app_id") ? params.get("app_id") : app_id);
        String res = "";
        try {
            res = Request.Patch(url)
                    .addHeader("X-Auth-Sign", signParams(params))
                    .addHeader("X-Auth-App-Id", appid)
                    .execute()
                    .returnContent()
                    .toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
