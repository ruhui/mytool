package com.ruhui.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCilent{

    /*接口Api*/
    private ApicilentParam apicilentParam;
    /*返回的APISERVSER*/
    private Object apiServer;

    public void setApicilentParam(ApicilentParam apicilentParam) {
        this.apicilentParam = apicilentParam;
        if (ApiClientHolder.INSTANCE.apicilentParam == null){
            throw new NullPointerException("apicilentParam is null,please setApicilentParam first");
        }
        if (ApiClientHolder.INSTANCE.apicilentParam.getBaseUrl() == null){
            throw new NullPointerException("baseUrl is null,please setBaseUrl first");
        }

        if (ApiClientHolder.INSTANCE.apicilentParam.getApiService() == null){
            throw new NullPointerException("apiService is null,please setApiService first");
        }
        Class tClass = ApiClientHolder.INSTANCE.apicilentParam.getApiService();

        apiServer = ApiClientHolder.INSTANCE.initCilent(tClass);

    }

    public static ApiCilent getInstance(){
        return ApiClientHolder.INSTANCE;
    }

    public static Object getApiService() {
        return ApiClientHolder.INSTANCE.apiServer;
    }

    private static class ApiClientHolder {
        static ApiCilent INSTANCE = new ApiCilent();
    }

    private ApiCilent() { }

    private <T> T initCilent(Class<T> tClass){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (!BuildConfig.DEBUG) {
            //relase模式下不抓包
            httpClientBuilder.proxy(Proxy.NO_PROXY);
        }
        httpClientBuilder.retryOnConnectionFailure(true);//默认重试一次，若需要重试N次，则要实现拦截器。
        httpClientBuilder.addInterceptor(apicilentParam.getInterceptor() == null
                ?new LogInterceptor():apicilentParam.getInterceptor());
        httpClientBuilder.retryOnConnectionFailure(false);
        httpClientBuilder.dns(new HandleDns());
        httpClientBuilder.connectTimeout(apicilentParam.getTimeout(), TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(apicilentParam.getTimeout(), TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(apicilentParam.getTimeout(), TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(apicilentParam.getBaseUrl())
                .build();
         return retrofit.create(tClass);
    }

    private class LogInterceptor implements Interceptor {
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

    private Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                .create();
    }

    public static class NullStringToEmptyAdapterFactory implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter();
        }
    }

    public static class StringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

}
