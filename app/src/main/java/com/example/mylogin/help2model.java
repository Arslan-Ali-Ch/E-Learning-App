package com.example.mylogin;

public class help2model {
    String title,fileurl;

    public help2model() {
    }

    public help2model(String title, String fileurl) {
        this.title = title;
        this.fileurl = fileurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
