package com.example.loginpage;

public class newsfeed_model {

    String purl , title;

    public newsfeed_model() {
    }

    public newsfeed_model(String purl, String title) {
        this.purl = purl;
        this.title = title;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
