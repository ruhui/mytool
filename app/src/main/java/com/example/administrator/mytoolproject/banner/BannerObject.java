package com.example.administrator.mytoolproject.banner;

import com.youth.banner.BaseObjectBanner;

public class BannerObject extends BaseObjectBanner {

    private String pictureUrl;

    private String videoUrl;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
