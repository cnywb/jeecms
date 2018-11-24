package com.jeecms.point.dao.order;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.vo.order.PointOrderVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public interface PointOrderDao {
	/***
	 * 保存订单数据
	 * @param pointOrder
	 * @return
	 */
	public  PointOrder save(PointOrder pointOrder);
	/***
	 * 根据用户分页查询数据
	 * @param queryVo 分页查询对象  
	 * @param userId 用户ID
	 * @return  Pagination
	 */
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId);
	
	/***
	 * 查询分页
	 * @param queryVo  分页信息
	 * @param PointOrderVo 查询条件
	 * @return Pagination
	 */
	public Pagination queryPagination(QueryVo queryVo,PointOrderVo PointOrderVo);
	/***
	 * 订单查询
	 * @param id 订单号
	 * @return   PointOrder
	 */
	public PointOrder findById(Long id);

	public PointOrder findByOrderKey(Long orderKey);
	 
	
	/***
	 * 订单状态
	 * @param id 订单号
	 * @param status 订单状态
	 * @return PointOrder
	 */
	public PointOrder update(PointOrder pointOrder);
}
