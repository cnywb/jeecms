package com.jeecms.point.entity;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.point.entity.base.BasePoint;

/**
 * Created by kui.yang on 15/5/30.
 * 抽奖历史记录
 * 用于记录用户的抽奖历史记录和判断用户每天已经抽过多次数
 */
public class PointLotteryHistory  extends BasePoint implements Serializable {

    /**
	 *序列化 
	 */
	private static final long serialVersionUID = 5977677232534044717L;
	
	/*当前登录会员用户ID*/
    private  Long userId;
    /*抽奖时间*/
    private Date lotteryDate;
    /*活动代码*/
    private String acitvityCode;
    /*中奖结果*/
    private String lotteryResult;
    /*中奖人姓名*/
    private String userName;
    /**  是否中奖 */
    private boolean isLotteried;
    /**代码*/
    private String openId;
    /**VIN*/
    private String vin;

    /**
     * 中奖商品ID
     */
    private Long productId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLotteryDate() {
        return lotteryDate;
    }

    public void setLotteryDate(Date lotteryDate) {
        this.lotteryDate = lotteryDate;
    }

    public String getAcitvityCode() {
        return acitvityCode;
    }

    public void setAcitvityCode(String acitvityCode) {
        this.acitvityCode = acitvityCode;
    }

    public String getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(String lotteryResult) {
        this.lotteryResult = lotteryResult;
    }

    public boolean getIsLotteried() {
        return isLotteried;
    }

    public void setIsLotteried(boolean isLotteried) {
        this.isLotteried = isLotteried;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	
}
