package com.serviceCaller.pojo;

import org.hibernate.validator.constraints.NotBlank;

public class ServiceRequest {
    private String operator;
    private String serviceName;

    @NotBlank(message = "操作人不能为空")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @NotBlank(message = "服务名不能为空")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
