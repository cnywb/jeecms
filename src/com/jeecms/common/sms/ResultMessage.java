package com.jeecms.common.sms;

/**
 * 发送短信返回对象
 * @author user
 *
 */
public class ResultMessage {

    private int stateCode;
    private String message;
    private Long token;
    private int fee;
    public int getStateCode() {
        return stateCode;
    }
    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Long getToken() {
        return token;
    }
    public void setToken(Long token) {
        this.token = token;
    }
    public int getFee() {
        return fee;
    }
    public void setFee(int fee) {
        this.fee = fee;
    }
}
