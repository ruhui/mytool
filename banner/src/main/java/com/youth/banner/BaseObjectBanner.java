package com.youth.banner;

public class BaseObjectBanner {

    private int type;//类型，暂时只有图片和视频 1为视频，2为图片


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
