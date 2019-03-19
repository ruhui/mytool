package com.ruhui.network;

import okhttp3.Interceptor;

/**
*  描述：初始化动作，设置Apicilent所需要的参数
*  创建作者：androidrh
*  创建时间：2019/3/13
*/
public class OkhttpInit {

    /*服务的地址*/
    private String baseUrl;
    /* 超时时间*/
    private int timeout = 5;
    /*拦截器*/
    private Interceptor interceptor;


    private OkhttpInit(){}

    private static class ApiClientHolder {
        static OkhttpInit INSTANCE = new OkhttpInit();
    }


    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public static OkhttpInit getInstance(){
        return ApiClientHolder.INSTANCE;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
