package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseFordRepairAddition;

public class FordRepairAddition extends BaseFordRepairAddition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -877682423314952385L;

	public FordRepairAddition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FordRepairAddition(Long vaid, String vbillId, String vroId,
			String vsstId, String vitemType, String vitemCode,
			String vitemName, Long nadditionFee, Long ndiscountRate,
			String vnotes, String vcreated, Date dcrtDate, String vupdated,
			Date dupDate) {
		super(vaid, vbillId, vroId, vsstId, vitemType, vitemCode, vitemName,
				nadditionFee, ndiscountRate, vnotes, vcreated, dcrtDate, vupdated,
				dupDate);
		// TODO Auto-generated constructor stub
	}
	
	

}
