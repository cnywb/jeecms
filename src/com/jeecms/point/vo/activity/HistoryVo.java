package com.jeecms.point.vo.activity;


public class HistoryVo {
	/*当前登录会员用户ID*/
    private  Long userId;
    /*抽奖时间*/
    private String lotteryDate;
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the lotteryDate
	 */
	public String getLotteryDate() {
		return lotteryDate;
	}
	/**
	 * @param lotteryDate the lotteryDate to set
	 */
	public void setLotteryDate(String lotteryDate) {
		this.lotteryDate = lotteryDate;
	}
	/**
	 * @return the acitvityCode
	 */
	public String getAcitvityCode() {
		return acitvityCode;
	}
	/**
	 * @param acitvityCode the acitvityCode to set
	 */
	public void setAcitvityCode(String acitvityCode) {
		this.acitvityCode = acitvityCode;
	}
	/**
	 * @return the lotteryResult
	 */
	public String getLotteryResult() {
		return lotteryResult;
	}
	/**
	 * @param lotteryResult the lotteryResult to set
	 */
	public void setLotteryResult(String lotteryResult) {
		this.lotteryResult = lotteryResult;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the isLotteried
	 */
	public boolean isLotteried() {
		return isLotteried;
	}
	/**
	 * @param isLotteried the isLotteried to set
	 */
	public void setLotteried(boolean isLotteried) {
		this.isLotteried = isLotteried;
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
