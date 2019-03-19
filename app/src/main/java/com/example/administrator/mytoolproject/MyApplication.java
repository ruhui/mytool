package com.example.administrator.mytoolproject;

import android.app.Application;

import com.ruhui.network.ApiCilent;
import com.ruhui.network.ApicilentParam;

import okhttp3.Interceptor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initApi();
    }

    private void initApi() {
        ApiCilent.getInstance().setApicilentParam(new ApicilentParam() {
            @Override
            public String getBaseUrl() {
                return "http://192.168.1.162:8080/appService/api/";
            }

            @Override
            public int getTimeout() {
                return 5;
            }

            @Override
            public Interceptor getInterceptor() {
                return new LogInterceptorTest();
            }

            @Override
            public Class<MyApiService> getApiService() {
                return MyApiService.class;
            }
        });
    }
}
