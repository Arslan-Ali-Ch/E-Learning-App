package com.example.mylogin;

import java.util.Date;

public class chatmodel {
    String user;
    String text;
    Long time;
    String messageid;

    public chatmodel() {
    }

    public chatmodel(String user, String text) {
        this.user = user;
        this.text = text;
        time=new Date().getTime();
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
