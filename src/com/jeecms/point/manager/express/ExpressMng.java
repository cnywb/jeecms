package com.jeecms.point.manager.express;

import com.jeecms.point.entity.Express;
/**
 * 收货人信息
 * @author wanglijun
 *
 */
public interface ExpressMng {
	/***
	 * 保存快递信息
	 * @param express
	 * @return Express
	 */
	public Express save(Express express);
	/***
	 * 根据查询
	 * @param id 快递信息ID
	 * @return Express
	 */
	public Express findById(Long id);
	/***
	 * 更新快递信息
	 * @param express  根据查询
	 * @return 根据查询
	 */
	public Express update(Express express);
	
	/***
	 * 根据订单号查询快递信息
	 * @param orderId 订单号
	 * @return Express
	 */
	public Express findByOrderId(Long orderId);
}
