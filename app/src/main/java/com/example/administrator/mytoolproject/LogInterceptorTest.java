package com.example.administrator.mytoolproject;

import com.ruhui.network.BuildConfig;
import com.ruhui.network.LogOkhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class LogInterceptorTest implements Interceptor {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        okhttp3.MediaType mediaType = response.body().contentType();
        //如果直接调用response.body().string()会关闭我们在内存里的资源，导致程序异常
        ResponseBody body = response.peekBody(1024 * 1024);
        String content = body.string();
        // 请求响应时间
        double time = (t2 - t1) / 1e6d;
        if (BuildConfig.DEBUG) {
            LogOkhttp.showRequestResponseLog(request, response, content, time);
        }
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}