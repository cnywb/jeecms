/**
 * 
 */
package com.jeecms.point.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanglijun
 *
 */
public class PointIncomQuery implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5156445950448570122L;
	/**ID*/
	private Long id;
	/**用户ID*/
	private Long userId;
	/**积分量*/
	private Long pointNum;
	/**积分收入的时间*/
	private Date incomeDate;
	/**积分收入的时间*/
	private Date expiryDate;
	/**积分规则编号*/
	private String pointRuleNo;
	/**积分规则名称*/
	private String pointRuleName;
	/**积分规则的ID*/
	private Long pointRuleId;
	/**备注信息*/
	private String memo;
	/**渠道 0PC 1微信*/
	private Integer channel;
	/**用户名*/
	private String username;
	/**订单号*/
	private Long orderId;
	/**RECORD_TYPE*/
	/**积分记录类型 0 收益 1 消费*/
	private Integer recordType;
	/**业务ID*/
	private Long businessId;
	/**积分类型*/
	private Integer pointType;
 
	
	
	/**
	 * 默认构造函数
	 */
	public PointIncomQuery() {
		super();
	}
	/**
	 * @param id
	 * @param userId
	 * @param pointNum
	 * @param incomeDate
	 * @param pointRuleNo
	 * @param pointRuleName
	 * @param pointRuleId
	 * @param memo
	 * @param channel
	 * @param username
	 */
	public PointIncomQuery(Long id, Long userId, Long pointNum,
			Date incomeDate,Date expiryDate,String pointRuleNo, String pointRuleName,
			Long pointRuleId, String memo, Integer channel, String username) {
		this.id = id;
		this.userId = userId;
		this.pointNum = pointNum;
		this.incomeDate = incomeDate;
		this.expiryDate=expiryDate;
		this.pointRuleNo = pointRuleNo;
		this.pointRuleName = pointRuleName;
		this.pointRuleId = pointRuleId;
		this.memo = memo;
		this.channel = channel;
		this.username = username;
	}
	
	
	
	/**
	 * @param id
	 * @param userId
	 * @param pointNum
	 * @param incomeDate
	 * @param expiryDate
	 * @param pointRuleNo
	 * @param pointRuleName
	 * @param pointRuleId
	 * @param memo
	 * @param channel
	 * @param recordType
	 */
	public PointIncomQuery(Long id, Long userId, Long pointNum,
			Date incomeDate, Date expiryDate, String pointRuleNo,
			String pointRuleName, Long pointRuleId, String memo,
			Integer channel, Integer recordType) {
		this.id = id;
		this.userId = userId;
		this.pointNum = pointNum;
		this.incomeDate = incomeDate;
		this.expiryDate = expiryDate;
		this.pointRuleNo = pointRuleNo;
		this.pointRuleName = pointRuleName;
		this.pointRuleId = pointRuleId;
		this.memo = memo;
		this.channel = channel;
		this.recordType = recordType;
	}
	/**
	 * @param id
	 * @param userId
	 * @param pointNum
	 * @param incomeDate
	 * @param expiryDate
	 * @param pointRuleNo
	 * @param pointRuleName
	 * @param pointRuleId
	 * @param memo
	 * @param channel
	 * @param username
	 * @param orderId
	 * @param recordType
	 * @param businessId
	 */
	public PointIncomQuery(Long id, Long userId, Long pointNum,
			Date incomeDate, Date expiryDate, String pointRuleNo,
			String pointRuleName, Long pointRuleId, String memo,
			Integer channel, String username, Long orderId, Integer recordType,
			Long businessId,Integer pointType) {
		this.id = id;
		this.userId = userId;
		this.pointNum = pointNum;
		this.incomeDate = incomeDate;
		this.expiryDate = expiryDate;
		this.pointRuleNo = pointRuleNo;
		this.pointRuleName = pointRuleName;
		this.pointRuleId = pointRuleId;
		this.memo = memo;
		this.channel = channel;
		this.username = username;
		this.orderId = orderId;
		this.recordType = recordType;
		this.businessId = businessId;
		this.pointType=pointType;
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
	 * @return the incomeDate
	 */
	public Date getIncomeDate() {
		return incomeDate;
	}
	/**
	 * @param incomeDate the incomeDate to set
	 */
	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
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
	 * @return the pointRuleId
	 */
	public Long getPointRuleId() {
		return pointRuleId;
	}
	/**
	 * @param pointRuleId the pointRuleId to set
	 */
	public void setPointRuleId(Long pointRuleId) {
		this.pointRuleId = pointRuleId;
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
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
	 * @return the recordType
	 */
	public Integer getRecordType() {
		return recordType;
	}
	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	/**
	 * @return the businessId
	 */
	public Long getBusinessId() {
		return businessId;
	}
	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	/**
	 * @return the pointType
	 */
	public Integer getPointType() {
		return pointType;
	}
	/**
	 * @param pointType the pointType to set
	 */
	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}
}
