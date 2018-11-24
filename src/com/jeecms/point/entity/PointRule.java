/**
 * 
 */
package com.jeecms.point.entity;

import com.jeecms.point.entity.base.BasePointRule;

/**
 * 
 * 积分规则
 * @author wanglijun
 *
 */
public class PointRule extends BasePointRule{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 5310086474445979836L;
	
	/**序列化*/
	public PointRule() {
		 super();
	}
	
	
	public PointRule(Long id){
		super(id);
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
	public PointRule(Long id, String pointRuleNo, Long pointNum,
			String pointRuleName, Integer showOrder, Integer cycle,
			String memo, Integer pointType) {
		 super(id, pointRuleNo, pointNum, pointRuleName, showOrder, cycle, memo, pointType);
	}
}
