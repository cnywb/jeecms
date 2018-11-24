package com.jeecms.point.dao.point;

import java.util.Date;
import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointIncome;
import com.jeecms.point.entity.PointIncomeCount;
import com.jeecms.point.vo.point.PointIncomeQueryVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public interface PointIncomeDao {
	/***
	 * 保存积分收入
	 * @param pointIncome
	 * @return  PointIncome
	 */
	public PointIncome save(PointIncome  pointIncome);
	/***
	 * 根据用户ID和规则代码查询该用户一次上限
	 * @param pointRuleNo
	 * @param userId
	 * @return
	 */
	public PointIncome findByPointRuleNo(String pointRuleNo,Long userId);
	/***
	 * 根据用户ID、规则代码及当前日期查询该用户一次上限
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户
	 * @param incomeDate 当前日期
	 * @return List<PointIncome>
	 */
	public List<PointIncome>  findByPointRuleNo(String pointRuleNo,Long userId,Date incomeDate);
	/***
	 * 根据用户ID，规则代码和起止日期统计积分收益次数，总积分收益
	 * @param pointRuleNo
	 * @param userId
	 * @param incomeStartDate
	 * @param incomeEndDate
	 * @return PointIncomeCount
	 */
	public PointIncomeCount findByPointRuleNo(String pointRuleNo, Long userId,Date incomeStartDate,Date incomeEndDate);
	
	/***
	 * 根据用户ID，规则代码和起止日期统计积分收益次数，总积分收益
	 * @param pointRuleNo
	 * @param userId
	 * @param incomeStartDate
	 * @param incomeEndDate
	 * @return PointIncomeCount
	 */
	public PointIncomeCount findCountByPointRuleNo(String pointRuleNo, Long userId);
	/***
	 * 分页查询
	 * @param queryVo 分页对象
	 * @param pointIncomeQueryVo 查询
	 * @return Pagination
	 */
	public Pagination queryPagination(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo);
	
	/***
	 * 分页查询
	 * @param queryVo 分页对象
	 * @param pointIncomeQueryVo 查询
	 * @return Pagination
	 */
	public Pagination paginationPayOut(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo);
	/***
	 * 根据用户ID分页查询
	 * @param queryVo 分页对象
	 *  @return Pagination
	 */
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId);
	
	/***
	 * 分页查询
	 * @param queryVo 分页对象
	 * @param pointIncomeQueryVo 查询
	 * @return Pagination
	 */
	public Pagination queryPaginationByUserId(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo);
	
	
	
}
