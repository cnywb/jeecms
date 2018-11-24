package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseFordRepairWeb;

public class FordRepairWeb extends BaseFordRepairWeb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4225628439374512809L;

	public FordRepairWeb() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FordRepairWeb(String vroId, String vcardNo, String vbillId,
			String vsstId, String vsstName, String vvin, String vrepairDate,
			String vfinishDate, String vbillDate, String vrepairType,
			Long nmileage, Long nbalanceFee, String vcreated, Date dcrtDate) {
		super(vroId, vcardNo, vbillId, vsstId, vsstName, vvin, vrepairDate,
				vfinishDate, vbillDate, vrepairType, nmileage, nbalanceFee, vcreated,
				dcrtDate);
		// TODO Auto-generated constructor stub
	}
	
	

}
