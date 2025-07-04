package com.example.administrator.mytoolproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ruhui.network.ApiCilent;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//为什么还要提交次数

public class ApiTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        //this is test
        LoginRequest resquestParent = new LoginRequest();
        ResquestParent httpClient = new ResquestParent<>(resquestParent, "");
        ((MyApiService)ApiCilent.getApiService()).login(httpClient)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseParent<LoginResponse>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("onError", "onError");
                    }

                    @Override
                    public void onNext(ResponseParent<LoginResponse> loginResponseResponseParent) {
                        Log.e("onNext", "onNext");
                    }
                });

    }
}
