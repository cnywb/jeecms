/**
 * 
 */
package com.jeecms.point.entity;

import com.jeecms.point.entity.base.BaseExpress;

/**
 * @author wanglijun
 *
 */
public class Express extends BaseExpress {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5039082432731045972L;

	/**
	 * 
	 */
	public Express() {
		 super();
	}

	/**
	 * @param id
	 */
	public Express(Long id) {
		super(id);
		 
	}

	/**
	 * @param userId
	 * @param province
	 * @param city
	 * @param disrict
	 * @param address
	 * @param zipCode
	 * @param phoneNo
	 * @param mobilePhoneNo
	 * @param sendee
	 * @param orderId
	 * @param expressNo
	 * @param expressCompany
	 */
	public Express(Long userId, String province, String city, String disrict,
			String address, String zipCode, String phoneNo,
			String mobilePhoneNo, String sendee, Long orderId,
			String expressNo, String expressCompany) {
		super(userId, province, city, disrict, address, zipCode, phoneNo,
				mobilePhoneNo, sendee, orderId, expressNo, expressCompany);
	}

}
