package com.jeecms.point.manager.point;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointPayout;
import com.jeecms.point.vo.point.PointPayoutQueryVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * 
 * 积分消费
 * @author wanglijun
 *
 */
public interface PointPayoutMng {
	/***
	 * 保存积分消费
	 * @param pointPayout
	 * @return
	 */
	public PointPayout save(PointPayout pointPayout);
	
	/***
	 * 产品兑换
	 * @param productId 产品ID
	 * @param userId 用户ID
	 * @param orderId 订单ID
	 * @return  boolean true 成功 false 失败
	 */
	public boolean exchangeProduct(Long productId,Integer userId,Long orderId);
	
	/***
	 * 查询分页
	 * @param queryVo  分页信息
	 * @param PointPayoutQueryVo 查询条件
	 * @return Pagination
	 */
	public Pagination queryPagination(QueryVo queryVo,PointPayoutQueryVo pointPayoutQueryVo);
	
	/***
	 * 查询分页
	 * @param queryVo  分页信息
	 * @param userId   用户ID
	 * @return Pagination 分页数据对象
	 */
	public Pagination queryPaginationForUser(QueryVo queryVo,Integer userId);
}
