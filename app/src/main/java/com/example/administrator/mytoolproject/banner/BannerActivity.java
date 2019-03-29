package com.example.administrator.mytoolproject.banner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mytoolproject.R;
import com.youth.banner.Banner;
import com.youth.banner.BaseObjectBanner;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        Banner mBanner = findViewById(R.id.banner);

        BannerLoader bannerLoader = new BannerLoader();

        List<BaseObjectBanner> list = new ArrayList();

        BannerObject bannerObject = new BannerObject();
        bannerObject.setType(2);
        bannerObject.setPictureUrl("这是图片地址");
        list.add(bannerObject);

        BannerObject bannerObject1 = new BannerObject();
        bannerObject1.setType(1);
        bannerObject1.setVideoUrl("这是视频地址");
        list.add(bannerObject1);

        mBanner.setImages(list);

        mBanner.setImageLoader(bannerLoader);
        mBanner.start();
    }


}
