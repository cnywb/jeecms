package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseTempDeal;

public class TempDeal extends BaseTempDeal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7066014575656906977L;

	/**
	 * 
	 */
	public TempDeal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param vin
	 * @param campaignCode
	 * @param amount
	 * @param useAmount
	 * @param validBeginDate
	 * @param validEndDate
	 * @param dealerServiceCode
	 * @param status
	 * @param createDate
	 * @param userName
	 * @param lowestAmount
	 * @param limitDeduct
	 */
	public TempDeal(long id, String name, String vin, String campaignCode,
			int amount, int useAmount, String validBeginDate,
			String validEndDate, String dealerServiceCode, String status,
			String createDate, String userName, int lowestAmount,
			int limitDeduct) {
		super(id, name, vin, campaignCode, amount, useAmount, validBeginDate,
				validEndDate, dealerServiceCode, status, createDate, userName,
				lowestAmount, limitDeduct);
		// TODO Auto-generated constructor stub
	}
	
	

}
