/**
 * 
 */
package com.jeecms.point.manager.order;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.vo.order.OrderExchengeVo;
import com.jeecms.point.vo.order.PointOrderVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */

public interface PointOrderMng {
	/***
	 * 保存订单数据
	 * @param pointOrder
	 * @return
	 */
	public  PointOrder save(PointOrder pointOrder);
	/***
	 * 根据ID查询订单信息
	 * @param id 订单号 
	 * @return PointOrder
	 */
	public PointOrder  findById(Long id);

	/**
	 * 根据订单唯一值查找
	 * @param orderKey
	 * @return
	 */
	public PointOrder findByOrderKey(Long orderKey);
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
	 * @return Pagination
	 */
	public Pagination queryPagination(QueryVo queryVo,PointOrderVo pointOrderVo);
	/***
	 *  更新发货信息
	 * @param id 订单号
	 * @param expressId 快递ID
	 * @param expressNo 快递单号
	 * @param expressCompany 快递公司
	 * @return PointOrder 订单信息
	 */
	public PointOrder updateSendInfo(Long id,Long expressId,String expressNo,String expressCompany);
	/**
	 * 更新订单状态
	 * @param id 订单号
	 * @param status 订单状态
	 * @return  PointOrder
	 */
	public PointOrder updateStatus(Long id,Integer status);
	
	/***
	 * 订单信息保存
	 * @param exchengeVo 订单
	 * @param unifiedUser 用户
	 * @param channel 渠道（PC，微信）
	 * @param payoutType 类型(秒杀、商品兑换、抽奖)
	 * @return 订单信息
	 */
	public PointOrder saveOrderInfo(OrderExchengeVo exchengeVo,UnifiedUser unifiedUser,int channel,int payoutType);
	
	
	/***
	 * 订单信息保存
	 * @param exchengeVo 订单
	 * @param unifiedUser 用户
	 * @param channel 渠道（PC，微信）
	 * @param payoutType 类型(秒杀、商品兑换、抽奖)
	 * @return 订单信息
	 */
	public PointOrder saveOrderForLottery(OrderExchengeVo exchengeVo,UnifiedUser unifiedUser,int channel);
	/*秒杀订单保存*/
	 PointOrder saveOrderForKill(OrderExchengeVo exchengeVo,Long unifiedUser,int channel);
}
