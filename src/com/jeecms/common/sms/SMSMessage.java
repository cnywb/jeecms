package com.jeecms.common.sms;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发送短信实体
 * @author user
 *
 */
public class SMSMessage {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSW");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * //机构编号
     */
    private String cid;
    private String uid;
    private String pwd;
    private String timestamp;
    private String mobiles[];
    private String content;
    private String atTime;
    private String dailyBeginTime;
    private String dailyEndTime;

    /**
     * //机构编号
     */
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        
        pwd = MD5.GetMD5Code(pwd + this.getTimestamp());
        this.pwd = pwd;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = sdf.format(timestamp);
    }

    public String[] getMobiles() {
        return mobiles;
    }

    public void setMobiles(String[] mobiles) {
        this.mobiles = mobiles;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAtTime() {
        return atTime;
    }

    public void setAtTime(Date atTime) {

        this.atTime = sdf2.format(atTime);
    }

    public String getDailyBeginTime() {
        return dailyBeginTime;
    }

    public void setDailyBeginTime(String dailyBeginTime) {
        this.dailyBeginTime = dailyBeginTime;
    }

    public String getDailyEndTime() {
        return dailyEndTime;
    }

    public void setDailyEndTime(String dailyEndTime) {
        this.dailyEndTime = dailyEndTime;
    }

}
