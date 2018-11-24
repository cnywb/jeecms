/**
 * 
 */
package com.jeecms.bbs.entity;

import java.util.Date;

import com.jeecms.bbs.entity.base.BaseBbsMemberCar;

/**
 * @author xuw
 *
 */
public class BbsMemberCar extends BaseBbsMemberCar {

	public BbsMemberCar() {
		super();
		 
	}

	public BbsMemberCar(Long id, Integer uuid, String vproduct,
			Date purchasedDate, String ocarImg, Date createDate,
			Integer createUser, Date updateDate, Integer updateUser) {
		super(id, uuid, vproduct, purchasedDate, ocarImg, createDate, createUser,
				updateDate, updateUser);
	}

	public BbsMemberCar(Long id, Integer uuid, String vproduct,
			Date purchasedDate, String ocarImg, String carId, Date createDate,
			Integer createUser, Date updateDate, Integer updateUser) {
		super(id, uuid, vproduct, purchasedDate, ocarImg, carId, createDate,
				createUser, updateDate, updateUser);
	}
	
	
		
}
