/**
 * 
 */
package com.jeecms.point.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wanglijun
 * 
 */
public class BasePointPayout extends BasePoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2225073328998394938L;
	
	public static String REF = "BasePointPayout";

	public static String PROP_ID = "id";

	public static String PROP_USER_ID = "userId";

	public static String PROP_POINT_NUM = "pointNum";

	public static String PROP_PAYOUT_DATE= "payoutDate";

	public static String PROP_POINT_RULE_NO = "pointRuleNo";

	public static String PROP_POINT_RULE_NAME = "pointRuleName";

	public static String PROP_POINT_RULE_ID = "pointRuleId";

	public static String PROP_POINT_MEMO = "memo";
	
	public static String PROP_ORDER_ID="orderId";
	
	
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

	/**
	 * 默认构造函数
	 */
	public BasePointPayout() {
		super();
	}
	
	
	
	
	

	/**
	 * @param id
	 */
	public BasePointPayout(Long id) {
		super();
		this.id = id;
	}



	


	/**
	 * @param id
	 * @param userId
	 * @param pointNum
	 * @param payoutDate
	 * @param pointRuleId
	 * @param pointRuleNo
	 * @param pointRuleName
	 * @param orderId
	 * @param memo
	 */
	public BasePointPayout(Long id, Long userId, Long pointNum,
			Date payoutDate, Integer payoutType, String pointRuleNo,
			String pointRuleName, Long orderId, String memo) {
		super();
		this.id = id;
		this.userId = userId;
		this.pointNum = pointNum;
		this.payoutDate = payoutDate;
		this.payoutType = payoutType;
		this.pointRuleNo = pointRuleNo;
		this.pointRuleName = pointRuleName;
		this.orderId = orderId;
		this.memo = memo;
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
 
	
}
