/**
 * 
 */
package com.jeecms.point.manager.point;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointRule;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public interface PointRuleMng {
	/***
	 * 保存积分规则
	 * @param pointRule
	 * @return PointRule
	 */
	public PointRule save(PointRule pointRule);
	/***
	 * 
	 * @param queryVo 查询参数
	 * @param pointRuleName 规则名称
	 * @param pintType 规则类型
	 * @return Pagination
	 */
	public Pagination getPage(QueryVo queryVo,String pointRuleName,Integer pointType);
	/**删除*/
	public PointRule remove(Long id);
	/***
	 * 查询规则
	 * @param id ID
	 * @return PointRule
	 */
	public PointRule findById(Long id);
	
	/***
	 * 查询规则
	 * @param pointRuleNo 规则代码
	 * @return PointRule
	 */
	public PointRule findByPointRuleNo(String pointRuleNo);
	/***
	 * 检查规则代码是否重新
	 * @param pointRuleNo  规则代码
	 * @return TRUE,表示重复 ，false表示不重复
	 */
	public boolean  checkPointRuleNo(String pointRuleNo);
	
	/***
	 * 检查规则代码是否重新
	 * @param id 编号
	 * @param pointRuleNo  规则代码
	 * @return TRUE,表示重复 ，false表示不重复
	 */
	public boolean  checkPointRuleNo(Long id,String pointRuleNo);
	/***
	 * 更新数据
	 * @param pointRule 更新数据
	 * @return 更新数据
	 */
	public PointRule update(PointRule pointRule);
	/***
	 * 查询所有积分规则;
	 * @return  List<PointRule>
	 */
	public List<PointRule> findAll();
	
}
