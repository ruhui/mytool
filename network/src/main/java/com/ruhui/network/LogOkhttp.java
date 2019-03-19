package com.ruhui.network;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class LogOkhttp {

    public static void showRequestResponseLog(Request request, Response response, String responseBody, double time){
        if (request == null || response == null) {
            return;
        }

        JSONObject json = new JSONObject();
        JSONObject requestJson = new JSONObject();
        JSONObject responseJson = new JSONObject();


        //request-----------------------------------------------------

        try {
            requestJson.put("请求接口地址：", request.url() + " 响应时间 " + time + " ms");
            requestJson.put("接口方法：", request.method());
            if (request.headers() != null) {
                JSONObject requestHeaderJson = new JSONObject();
                Map<String, List<String>> requsetHeadersMap = request.headers().toMultimap();
                Set<String> requsetKeys = requsetHeadersMap.keySet();
                for (String key : requsetKeys) {
                    List<String> value = requsetHeadersMap.get(key);
                    requestHeaderJson.put(key, value.get(0));
                }
                requestJson.put("headers", requestHeaderJson);
            }


            requestJson.put("body", stringifyRequestBody(request));

            //response-----------------------------------------------------

            responseJson.put("返回码", response.code());


            if (response.headers() != null) {
                JSONObject reponseHeaderJson = new JSONObject();
                Map<String, List<String>> responseHeadersMap = response.headers().toMultimap();
                Set<String> responseKeys = responseHeadersMap.keySet();
                for (String key : responseKeys) {
                    List<String> value = responseHeadersMap.get(key);
                    reponseHeaderJson.put(key, value.get(0));
                }
                responseJson.put("headers", reponseHeaderJson);
            }

            responseJson.put("返回数据", responseBody);

            json.put("请求数据：", requestJson);
            json.put("响应数据", responseJson);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Logger.d(JsonUtil.formatJson(json.toString()));
    }


    private static String stringifyRequestBody(Request request) {
        try {

            Request copy = request.newBuilder().build();
            if (copy != null) {
                RequestBody body = copy.body();
                if (body != null) {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    return buffer.readUtf8();
                } else {
                    return "null";
                }

            } else {
                return "null";
            }

        } catch (final IOException e) {
            return "did not work";
        }
    }


}
