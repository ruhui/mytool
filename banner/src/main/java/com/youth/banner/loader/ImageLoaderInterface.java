package com.youth.banner.loader;

import android.content.Context;
import android.view.View;

import java.io.Serializable;


public interface ImageLoaderInterface<T extends View> extends Serializable {

    void displayImage(Context context, Object path, T imageView);
    //type 1为视频，2为图片
    T createImageView(Context context, int type);
}
