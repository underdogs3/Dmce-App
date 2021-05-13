package com.example.loginpage;

public class course_model {

    String s_name, p_name , purl;


    public course_model() {
    }

    public course_model(String s_name, String p_name, String purl) {
        this.s_name = s_name;
        this.p_name = p_name;
        this.purl = purl;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}


