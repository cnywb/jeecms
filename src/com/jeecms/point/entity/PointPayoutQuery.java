/**
 * 
 */
package com.jeecms.point.entity;

import java.util.Date;

import com.jeecms.point.entity.base.BasePointPayout;

/**
 * 积分支出信息
 * 
 * @author wanglijun
 *
 */
public class PointPayoutQuery extends BasePointPayout {

	
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5830267894662039521L;
	/**积分支出ID*/
	private Long  id;
	/**用户ID*/
	private Long userId;
	/**积分数量*/
	private Long pointNum;
	/**支出日期*/
	private Date payoutDate;
	/**支付项类型*/
	private Integer payoutType;
	/**积分规则编号*/
	private String pointRuleNo;
	/**积分规则名称*/
	private String pointRuleName;
	/**订单号*/
	private Long orderId;
	/**备注信息*/
	private String memo;
	/**渠道 0PC 1微信*/
	private Integer channel;
	/***
	 * 用户名
	 */
	private String username;
	 
	public PointPayoutQuery() {
		 super();
	}
	
	
	
	/**
	 * @param id
	 * @param userId
	 * @param pointNum
	 * @param payoutDate
	 * @param payoutType
	 * @param pointRuleNo
	 * @param pointRuleName
	 * @param orderId
	 * @param memo
	 * @param channel
	 * @param username
	 */
	public PointPayoutQuery(Long id, Long userId, Long pointNum,
			Date payoutDate,Integer payoutType, String pointRuleNo,
			String pointRuleName, Long orderId, String memo, Integer channel,
			String username) {
		this.id = id;
		this.userId = userId;
		this.pointNum = pointNum;
		this.payoutDate = payoutDate;
		this.payoutType = payoutType;
		this.pointRuleNo = pointRuleNo;
		this.pointRuleName = pointRuleName;
		this.orderId = orderId;
		this.memo = memo;
		this.channel = channel;
		this.username = username;
	}



	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



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
	 * @return the pointNum
	 */
	public Long getPointNum() {
		return pointNum;
	}



	/**
	 * @param pointNum the pointNum to set
	 */
	public void setPointNum(Long pointNum) {
		this.pointNum = pointNum;
	}



	/**
	 * @return the payoutDate
	 */
	public Date getPayoutDate() {
		return payoutDate;
	}



	/**
	 * @param payoutDate the payoutDate to set
	 */
	public void setPayoutDate(Date payoutDate) {
		this.payoutDate = payoutDate;
	}



 


	/**
	 * @return the pointRuleNo
	 */
	public String getPointRuleNo() {
		return pointRuleNo;
	}



	/**
	 * @param pointRuleNo the pointRuleNo to set
	 */
	public void setPointRuleNo(String pointRuleNo) {
		this.pointRuleNo = pointRuleNo;
	}



	/**
	 * @return the pointRuleName
	 */
	public String getPointRuleName() {
		return pointRuleName;
	}



	/**
	 * @param pointRuleName the pointRuleName to set
	 */
	public void setPointRuleName(String pointRuleName) {
		this.pointRuleName = pointRuleName;
	}



	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}



	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}



	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}



	/**
	 * @return the channel
	 */
	public Integer getChannel() {
		return channel;
	}



	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Integer channel) {
		this.channel = channel;
	}



	/**
	 * @return the payoutType
	 */
	public Integer getPayoutType() {
		return payoutType;
	}



	/**
	 * @param payoutType the payoutType to set
	 */
	public void setPayoutType(Integer payoutType) {
		this.payoutType = payoutType;
	}
	
	
}
