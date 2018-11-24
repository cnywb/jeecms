/**
 * 
 */
package com.jeecms.point.dao.point;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointRule;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public  interface PointRuleDao {
	/***
	 * 显示所有积分规则
	 * @return
	 */
	public List<PointRule> findAll();
	/***
	 * 保存积分规则
	 * @param pointRule 规则
	 * @return  PointRule
	 */
	public PointRule save(PointRule pointRule);
	
	/***
	 * 查询参数
	 * @param queryVo
	 * @param pointRuleName
	 * @param pintType
	 * @return Pagination
	 */
	public Pagination queryPointRule(QueryVo queryVo,String pointRuleName,Integer pointType);
	/**
	 * 删除对象
	 * @param id 对象ID
	 * @return true 删除
	 */
	public  PointRule remove(Long id);
	
	/***
	 * 根据ID查询积分规则
	 * @param id
	 * @return PointRule
	 */
	public PointRule  findById(Long id);
	/***
	 * 根据规则代码查询积分规则对象
	 * @param pointRuleNo  积分规则代码
	 * @return   PointRule
	 */
	public PointRule  findByPointRuleNo(String pointRuleNo);
	
	/***
	 * 根据规则代码查询积分规则对象
	 * @param id
	 * @param pointRuleNo  积分规则代码
	 * @return   PointRule
	 */
	public PointRule  findByPointRuleNo(Long id,String pointRuleNo);
	
	/***
	 * 更新数据
	 * @param pointRule 更新数据
	 * @return 更新数据
	 */
	public PointRule update(PointRule pointRule);
 

}
