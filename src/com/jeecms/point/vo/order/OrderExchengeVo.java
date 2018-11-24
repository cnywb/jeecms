/**
 * 
 */
package com.jeecms.point.vo.order;

import java.io.Serializable;

/**
 * 订单积分交换
 * 
 * @author wanglijun
 * 
 */
public class OrderExchengeVo implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3553439065704903538L;

	/** 产品ID */
	private Long productId;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 县区 */
	private String county;
	/** 地址 */
	private String address;
	/** 邮编 */
	private String zipCode;
	/** 电话 */
	private String phoneNo;
	/** 移动电话 */
	private String mobilePhoneNo;
	/** 收货人 */
	private String sendee;
	/** 备注 */
	private String memo;
	/***/
	private String ruleName;
	/**活动代码*/
	private String activityCode;
	private String openId;

	private Long orderId;
	/**
	 * 中奖后的记录ID
	 */
	private Long lotteryHistoryId;

	public OrderExchengeVo() {
		super();
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

 

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderExchengeVo [productId=" + productId + ", province="
				+ province + ", city=" + city + ", county=" + county
				+ ", address=" + address + ", zipCode=" + zipCode
				+ ", phoneNo=" + phoneNo + ", mobilePhoneNo=" + mobilePhoneNo
				+ ", sendee=" + sendee + ", memo=" + memo + "]";
	}
	
	

	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the mobilePhoneNo
	 */
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}

	/**
	 * @param mobilePhoneNo
	 *            the mobilePhoneNo to set
	 */
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}

	/**
	 * @return the sendee
	 */
	public String getSendee() {
		return sendee;
	}

	/**
	 * @param sendee
	 *            the sendee to set
	 */
	public void setSendee(String sendee) {
		this.sendee = sendee;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public Long getLotteryHistoryId() {
		return lotteryHistoryId;
	}

	public void setLotteryHistoryId(Long lotteryHistoryId) {
		this.lotteryHistoryId = lotteryHistoryId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
