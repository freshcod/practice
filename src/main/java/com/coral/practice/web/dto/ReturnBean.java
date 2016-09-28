package com.coral.practice.web.dto;

/**
 * Created by qiuhai on 2016/7/23.
 */
public class ReturnBean {
    private Integer status;
    private String message;

    public ReturnBean(Integer status,String message){
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReturnBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
