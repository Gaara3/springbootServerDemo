package com.serviceCaller.pojo;

import org.hibernate.validator.constraints.NotBlank;

public class LoginReq {
    private String operator;
    private String password;

    @NotBlank(message = "请输入用户名")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @NotBlank(message = "请输入密码")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
