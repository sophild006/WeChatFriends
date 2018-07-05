package com.stworks.friends.bean;

/**
 * Created by wwq on 2018/7/4.
 */

public class Images {

    private String url;

    private int w;
    private int h;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "Images{" +
                "url='" + url + '\'' +
                '}';
    }
}
