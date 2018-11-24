/**
 * 
 */
package com.jeecms.point.vo.point;

import java.io.Serializable;

/**
 * 积分消费查询
 * 
 * @author wanglijun
 * 
 */
public class PointPayoutQueryVo implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3243110338774863674L;
	/***
	 * 订单号
	 */
	private Long orderId;
	/** 用户名 */
	private String username;
	/** 积分消费起期 */
	private String startDate;
	/** 积分消费止期 */
	private String endDate;
	/** 积分消费的类型 */
	private Long payoutType;
	

	public PointPayoutQueryVo() {
		super();
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the payoutType
	 */
	public Long getPayoutType() {
		return payoutType;
	}

	/**
	 * @param payoutType
	 *            the payoutType to set
	 */
	public void setPayoutType(Long payoutType) {
		this.payoutType = payoutType;
	}

}
