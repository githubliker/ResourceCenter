package com.surface.resourcecenter.ui.sample.bean;

public class SampleImageBean {


    /**
     * code : 20000
     * msg : 操作成功
     * data : http://127.0.0.1:9529/sample_img/9157b2cc667144a79ee6f8e96831d351.jpg
     * token : 71937e0d-707f-4dbc-8a80-33c248d49495
     */

    private int code;
    private String msg;
    private String data;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
