/**
 * 
 */
package com.jeecms.point.vo.point;


/**
 * 
 * 积分收益查询条件
 * @author wanglijun
 *
 */
public class PointIncomeQueryVo {
	/**
	 * 积分规则代码
	 * */
	private String pointRuleNo;
	/**
	 * 积分规则名称
	 * */
	private String pointRuleName;
	/**用户ID*/
	private Long userId;
	/**用户名*/
	private String username; 
	/**积分类型*/
	private  Integer pointType;
	/**积分收益起日期*/
	private String startDate;
	/**积分收益止期*/
	private String endDate;
	/*** 订单号*/
	private Long orderId;
	/**类型 0 收益 1 消费*/
	private Integer recordType;
	
	public PointIncomeQueryVo() {
	   super();
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
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
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	
	
}
