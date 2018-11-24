/**
 * 
 */
package com.jeecms.point.entity.base;

import java.io.Serializable;

/**
 * 积分规则
 * @author wanglijun
 *
 */
public class BasePointRule extends  BasePoint implements Serializable{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7638294779331812488L;
	
	public static String REF = "BasePointRule";
	
	public static String PROP_ID = "id";
	
	public static String PROP_POINT_RULE_NO="pointRuleNo";
	
	public static String PROP_POINT_NUM="pointNum";
	
	public static String PROP_POINT_RULE_NAME="pointRuleName";
	
	public static String PROP_SHOWORDER="showOrder";
	
	public static String PROP_CYCLE_="cycle";
	
	public static String PROP_MEMO="memo";
	
	public static String PROP_POINT_TYPE="pointType";
	
	/**规则ID*/
	private Long  id;
	/**规则编号*/
	private String pointRuleNo;
	/**规则的积分*/
	private Long pointNum;
	/**规则名称*/
	private String pointRuleName;
	/**显示顺序*/
	private Integer showOrder;
	/**周期性类型-CYCLE*/
	private Integer cycle;
	/**周期性次数*/
	private Integer cycleTime;
	/**最高积分*/
	private Long maxPointNum;
	/**最高积分描述*/
	private String maxPointNumMemo;
	/**备注*/
	private String memo;
	/**积分类型 0 奖励 1 惩罚;*/
	private Integer  pointType;
	/**积分有效期类型 0 一个月,1季度  2 半年 3 一年 （默认）4 两年 5三年 6 五年 7 十年*/
	private Integer expiryDateType;
	/**积分使用渠道  渠道 0 微信&网站 1网站 2 微信*/
	private Integer channel;

	
	public BasePointRule() {
		super();
	}

	/**
	 * @param id
	 */
	public BasePointRule(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param pointRuleNo
	 * @param pointNum
	 * @param pointRuleName
	 * @param showOrder
	 * @param cycle
	 * @param memo
	 * @param pointType
	 */
	public BasePointRule(Long id, String pointRuleNo, Long pointNum,
			String pointRuleName, Integer showOrder, Integer cycle,
			String memo, Integer pointType) {
		super();
		this.id = id;
		this.pointRuleNo = pointRuleNo;
		this.pointNum = pointNum;
		this.pointRuleName = pointRuleName;
		this.showOrder = showOrder;
		this.cycle = cycle;
		this.memo = memo;
		this.pointType = pointType;
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
	 * @return the showOrder
	 */
	public Integer getShowOrder() {
		return showOrder;
	}

	/**
	 * @param showOrder the showOrder to set
	 */
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	/**
	 * @return the cycle
	 */
	public Integer getCycle() {
		return cycle;
	}

	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
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
	 * @return the cycleTime
	 */
	public Integer getCycleTime() {
		return cycleTime;
	}

	/**
	 * @param cycleTime the cycleTime to set
	 */
	public void setCycleTime(Integer cycleTime) {
		this.cycleTime = cycleTime;
	}

	/**
	 * @return the maxPointNum
	 */
	public Long getMaxPointNum() {
		return maxPointNum;
	}

	/**
	 * @param maxPointNum the maxPointNum to set
	 */
	public void setMaxPointNum(Long maxPointNum) {
		this.maxPointNum = maxPointNum;
	}

	/**
	 * @return the expiryDateType
	 */
	public Integer getExpiryDateType() {
		return expiryDateType;
	}

	/**
	 * @param expiryDateType the expiryDateType to set
	 */
	public void setExpiryDateType(Integer expiryDateType) {
		this.expiryDateType = expiryDateType;
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
	 * @return the maxPointNumMemo
	 */
	public String getMaxPointNumMemo() {
		return maxPointNumMemo;
	}

	/**
	 * @param maxPointNumMemo the maxPointNumMemo to set
	 */
	public void setMaxPointNumMemo(String maxPointNumMemo) {
		this.maxPointNumMemo = maxPointNumMemo;
	}
	
	
}
