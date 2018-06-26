package com.serviceCaller.pojo;

public class LoginRes {
    private String result;
    private String token;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginRes(){}

    public LoginRes(String result){
        this.setToken("");
        this.setResult(result);
    }
}
