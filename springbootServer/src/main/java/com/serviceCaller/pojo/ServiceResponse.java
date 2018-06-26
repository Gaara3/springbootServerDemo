package com.serviceCaller.pojo;

public class ServiceResponse {
    private String serviceName;
    private int missionStatus;
    private String timestamp;
    private int responseStatus;

    public ServiceResponse(){}

    public ServiceResponse(String serviceName){
        this.setServiceName(serviceName);
        this.setResponseStatus(1);
        this.setMissionStatus(1);
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(int missionStatus) {
        this.missionStatus = missionStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
