package com.jeecms.point.vo.activity;

import java.util.Date;

/**
 * Created by kui.yang on 15/5/26.
 */
public class ActivityVO {
    private Long id;
    private String startTime;
    private String endTime;
    private String name;
    private String type;
    private String probability1;
    private String probability2;
    private Integer costPoints;
    /*每人每天抽奖最多次数限制*/
    private Integer lotteryLimitForDay;

    /*秒杀活动开始时间*/
    private Integer killStartTime;
    /*当天秒杀活动结束时间*/
    private Integer killEndTime;
    /*每周几进行秒杀*/
    private String killOfWeek;
    private boolean status;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProbability1() {
        return probability1;
    }

    public void setProbability1(String probability1) {
        this.probability1 = probability1;
    }

    public String getProbability2() {
        return probability2;
    }

    public void setProbability2(String probability2) {
        this.probability2 = probability2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCostPoints() {
        return costPoints;
    }

    public void setCostPoints(Integer costPoints) {
        this.costPoints = costPoints;
    }

    public Integer getLotteryLimitForDay() {
        return lotteryLimitForDay;
    }

    public void setLotteryLimitForDay(Integer lotteryLimitForDay) {
        this.lotteryLimitForDay = lotteryLimitForDay;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getKillStartTime() {
        return killStartTime;
    }

    public void setKillStartTime(Integer killStartTime) {
        this.killStartTime = killStartTime;
    }

    public Integer getKillEndTime() {
        return killEndTime;
    }

    public void setKillEndTime(Integer killEndTime) {
        this.killEndTime = killEndTime;
    }

    public String getKillOfWeek() {
        return killOfWeek;
    }

    public void setKillOfWeek(String killOfWeek) {
        this.killOfWeek = killOfWeek;
    }
}
