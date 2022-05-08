package com.example.mylogin;

public class video2model {
    String title,videourl;

    public video2model() {
    }

    public video2model(String title, String videourl) {
        this.title = title;
        this.videourl = videourl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
