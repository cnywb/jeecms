package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseTempElec;

public class TempElec extends BaseTempElec {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6284311304354211877L;

	/**
	 * 
	 */
	public TempElec() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TempElec(long accountId, String vin, String roNo,
			String dealerServiceCode, String usedDate, long usedAmount,
			long lostAmount, String eleType) {
		super(accountId, vin, roNo, dealerServiceCode, usedDate, usedAmount,
				lostAmount, eleType);
		// TODO Auto-generated constructor stub
	}
	
	

}
