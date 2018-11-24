/**
 * 
 */
package com.jeecms.point.entity;

/**
 * 查询统计
 * @author wanglijun
 *
 */
public class PointIncomeCount {
	/**累计统计*/
	private Long totalCount;
	/**总计分积分*/
	private Long totalPointNum;
	
	public PointIncomeCount() {
		 super();
		 this.totalCount=0L;
		 this.totalPointNum=0L;
	}

	/**
	 * @param totalCount
	 * @param totalPointNum
	 */
	public PointIncomeCount(Long totalCount, Long totalPointNum) {
		this.totalCount = totalCount;
		this.totalPointNum = totalPointNum;
	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the totalPointNum
	 */
	public Long getTotalPointNum() {
		return totalPointNum;
	}

	/**
	 * @param totalPointNum the totalPointNum to set
	 */
	public void setTotalPointNum(Long totalPointNum) {
		this.totalPointNum = totalPointNum;
	}
}
