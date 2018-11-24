/**
 * 
 */
package com.jeecms.point.dao.point;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointPayout;
import com.jeecms.point.vo.point.PointPayoutQueryVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public interface PointPayoutDao {
	/***
	 * 保存
	 * @param pointPayout
	 * @return PointPayout
	 */
	public PointPayout save(PointPayout pointPayout);
	
	/***
	 * 根据用户分页查询
	 * @param queryVo 分页
	 * @param pointPayoutQueryVo 查询条件
	 * @return
	 */
	public Pagination queryPagination(QueryVo queryVo,PointPayoutQueryVo pointPayoutQueryVo);
	/***
	 * 根据用户分页查询
	 * @param queryVo 分页对象
	 * @param userId 用户ID
	 * @return Pagination
	 */
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId);
}
