/**
 * 
 */
package com.jeecms.point.entity;

import com.jeecms.point.entity.base.BaseExpressCommon;

/**
 * 
 * 用户常用配送地址信息表
 * @author wanglijun
 *
 */
public class ExpressCommon extends BaseExpressCommon {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3235022271745073421L;

	/**
	 * 
	 */
	public ExpressCommon() {
		super();
	}

	/**
	 * @param id
	 */
	public ExpressCommon(Long id) {
		super(id);
	}

	/**
	 * @param id
	 * @param userId
	 * @param province
	 * @param city
	 * @param disrict
	 * @param address
	 * @param zipCode
	 * @param isDefault
	 * @param phoneNo
	 * @param mobilePhoneNo
	 */
	public ExpressCommon(Long id, Long userId, String province, String city,
			String disrict, String address, String zipCode, Integer isDefault,
			String phoneNo, String mobilePhoneNo,String sendee) {
		super();
	}

}
