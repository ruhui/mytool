package com.example.administrator.mytoolproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.mytoolproject.banner.BannerActivity;
import com.ruhui.network.ApiCilent;
import com.ruhui.network.ApicilentParam;

import okhttp3.Interceptor;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_apitest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_api = new Intent(MainActivity.this, ApiTestActivity.class);
                startActivity(intent_api);
            }
        });

        findViewById(R.id.btn_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_api = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(intent_api);
            }
        });

    }

}
