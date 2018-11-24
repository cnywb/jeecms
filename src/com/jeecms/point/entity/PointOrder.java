/**
 * 
 */
package com.jeecms.point.entity;

import java.util.Date;

import com.jeecms.point.entity.base.BasePointOrder;

/**
 * 
 * 
 * @author wanglijun
 *
 */
public class PointOrder extends BasePointOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6678238993142966212L;

	/**
	 * 订单标示，用于抽奖生成订单时关联抽奖结果记录，保存抽奖结果的ID
	 * 防止多次提交订单
	 */
	private Long orderKey;

	/**
	 * 
	 */
	public PointOrder() {
		 super();
	}

	/**
	 * @param id
	 */
	public PointOrder(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param userId
	 * @param totalPointNum
	 * @param orderDate
	 * @param status
	 * @param type
	 * @param memo
	 * @param num
	 * @param payoutDate
	 */
	public PointOrder(Long id, Long userId, Long totalPointNum, Date orderDate,
			Integer status, Integer type, String memo, Long num, Date payoutDate) {
		super(id, userId, totalPointNum, orderDate, status, type, memo, num, payoutDate);
	}

	public Long getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(Long orderKey) {
		this.orderKey = orderKey;
	}
}
