/**
 * 
 */
package com.jeecms.point.entity;

import java.util.Date;

import com.jeecms.point.entity.base.BasePointIncome;

/**
 * @author wanglijun
 *
 */
public class PointIncome extends BasePointIncome {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5170502340094668356L;

	/**
	 * 
	 */
	public PointIncome() {
	    super();
	}

	/**
	 * @param id
	 */
	public PointIncome(Long id) {
		super(id);
 
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
	 */
	public PointIncome(Long id, Long userId, Long pointNum, Date incomeDate,
			String pointRuleNo, String pointRuleName, Long pointRuleId,
			String memo) {
		super(id, userId, pointNum, incomeDate, pointRuleNo, pointRuleName,
				pointRuleId, memo);
	  
	}

}
