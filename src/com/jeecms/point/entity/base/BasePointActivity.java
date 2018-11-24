package com.jeecms.point.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kui.yang on 15/5/25.
 * 活动数据
 */
public class BasePointActivity extends  BasePoint implements Serializable {
    /*活动开始时间*/
    private Date startTime;
    /*活动结束时间*/
    private Date endTime;
    /*活动类型：1.抽奖 2.秒杀 3.兑换*/
    private String type;

    /**
     * 中奖概率 分母值 如，每10000次，可以中奖5次，则分母值为10000，分子值为5
     */
    private Integer probability1;
    /*中奖概率 分子值*/
    private Integer probability2;
    /*活动名称*/
    private String name;
    /*活动代码*/
    private String code;

    /*每次抽奖所需要消耗的积分数*/
    private Integer costPoints;
    /*每人每天抽奖最多次数限制*/
    private Integer lotteryLimitForDay;
    /*活动状态*/
    private  boolean status;
    /*秒杀活动开始时间*/
    private Integer killStartTime;
    /*当天秒杀活动结束时间 暂时不用，默认一直到晚上12点*/
    private Integer killEndTime;
    /*每周几进行秒杀*/
    private String killOfWeek;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProbability1() {
        return probability1;
    }

    public void setProbability1(Integer probability1) {
        this.probability1 = probability1;
    }

    public Integer getProbability2() {
        return probability2;
    }

    public void setProbability2(Integer probability2) {
        this.probability2 = probability2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
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
