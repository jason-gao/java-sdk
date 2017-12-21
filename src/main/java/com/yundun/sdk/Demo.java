package com.yundun.sdk;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) throws Exception {
        YundunSDK sdk = new YundunSDK("apiId", "appSecret");

        Map<String, Object> params = new HashMap<>();
        params.put("page", "0");
        params.put("per_page", "20");
        params.put("order", "id desc");
        params.put("domain", "yundun.com");

        String postRes = sdk.post("Web.Domain.list", params);

        System.out.println(postRes);
    }
}
