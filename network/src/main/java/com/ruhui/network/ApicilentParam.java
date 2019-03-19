package com.ruhui.network;

import okhttp3.Interceptor;

public abstract class ApicilentParam<T> {
    /*获取baseurl*/
    public abstract String getBaseUrl();
    /*设置超时时间*/
    public abstract int getTimeout();
    /*设置拦截器*/
    public abstract Interceptor getInterceptor();
    /*接口Api*/
    public abstract Class<T> getApiService();
}
