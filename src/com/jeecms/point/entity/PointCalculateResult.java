/**
 * 
 */
package com.jeecms.point.entity;

/**
 * 积分计算结果
 * @author wanglijun
 *
 */
public class PointCalculateResult {
	/**计算器默认false*/
	private boolean result=false;
	/**积分规则代码*/
	private String ruleNo;
	/**积分规则名称*/
	private String ruleName;
	/**规则的积分*/
	private Long num;
	/**规则的最大积分*/
	private Long maxNum;
	/**最后一次积分*/
	private Long lastNum;
	
	/**默认函数*/
	public PointCalculateResult() {
		 super();
	}
	
	/**
	 * @param result  结果
	 * @param ruleNo  规则代码
	 * @param num 积分
	 */
	public PointCalculateResult(boolean result, String ruleNo, Long num) {
		this.result = result;
		this.ruleNo = ruleNo;
		this.num = num;
	}
	
	
	
	
	/**
	 * @param result
	 * @param ruleNo
	 * @param ruleName
	 * @param num
	 * @param maxNum
	 */
	public PointCalculateResult(boolean result, String ruleNo, String ruleName,
			Long num, Long maxNum) {
		this(result, ruleNo, maxNum);
		this.num = num;
		this.maxNum = maxNum;
	}




	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	/**
	 * @return the ruleNo
	 */
	public String getRuleNo() {
		return ruleNo;
	}
	/**
	 * @param ruleNo the ruleNo to set
	 */
	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
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
	/**
	 * @return the num
	 */
	public Long getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(Long num) {
		this.num = num;
	}
	/**
	 * @return the maxNum
	 */
	public Long getMaxNum() {
		return maxNum;
	}
	/**
	 * @param maxNum the maxNum to set
	 */
	public void setMaxNum(Long maxNum) {
		this.maxNum = maxNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointCalculateResult [result=" + result + ", ruleNo=" + ruleNo
				+ ", ruleName=" + ruleName + ", num=" + num + ", maxNum="
				+ maxNum + "]";
	}

	/**
	 * @return the lastNum
	 */
	public Long getLastNum() {
		return lastNum;
	}

	/**
	 * @param lastNum the lastNum to set
	 */
	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}
	
	
	
}
