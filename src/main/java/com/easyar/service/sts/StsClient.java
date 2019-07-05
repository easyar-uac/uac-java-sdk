package com.easyar.service.sts;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class StsClient {

    private static OkHttpClient httpClient = new OkHttpClient();
    private static final String url = "/token/v2";

    private String authTokenServer = "https://uac.easyar.com";

    public StsClient() {}

    public StsClient(String authTokenServer) {
        this.authTokenServer = authTokenServer;
    }

    /**
     * 取认证token信息
     * @param apiKey
     * @param apiSecret
     * @param expires   设置token的过期时间,单位为秒
     * @return
     * @throws IOException
     */
    public SecurityToken getSecurityToken(String apiKey, String apiSecret, int expires, List<AccessControl> acl) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        SortedMap<String, String> params = new TreeMap<>();
        params.put("apiKey", apiKey);
        params.put("expires", Integer.toString(expires));
        params.put("timestamp", Long.toString(System.currentTimeMillis()));
        params.put("acl", mapper.writeValueAsString(acl));
        params.put("signature", this.getSign(params, apiSecret));

        String json = mapper.writeValueAsString(params);

        byte[] body = this.getResponse(json);

        ResultInfo resultInfo = mapper.readValue(body, ResultInfo.class);

        return resultInfo.getResult();
    }

    private String getSign(SortedMap<String, String> params, String apiSecret) {
        StringBuilder builder = new StringBuilder();
        params.forEach((key, value) -> builder.append(key + value));

        return this.sha256(builder.toString() + apiSecret);
    }

    private String sha256(String str) {
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("sha-256");
            digest.update(str.getBytes());
            byte[] bytes = digest.digest();

            for (byte b: bytes) {
                String hex = Integer.toHexString(b & 0XFF);
                builder.append(hex.length() == 1 ? "0" + hex : hex);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return builder.toString();
    }

    private byte[] getResponse(String json) throws Exception {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(this.authTokenServer + url)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            byte[] bytes = response.body().bytes();
            if (response.code() != 200) {
                throw new Exception(new String(bytes));
            }
            return bytes;
        }
    }
}
