package com.example.mylogin;

public class coursemodel {
    String coursetitle;
    boolean check;
    public coursemodel(){}

    public String getCoursetitle() {
        return coursetitle;
    }

    public void setCoursetitle(String coursetitle) {
        this.coursetitle = coursetitle;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public coursemodel(String coursetitle, boolean check) {
        this.coursetitle = coursetitle;
        this.check = check;
    }
}
