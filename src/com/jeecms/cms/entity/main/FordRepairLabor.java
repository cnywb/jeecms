package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseFordRepairLabor;

public class FordRepairLabor extends BaseFordRepairLabor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8011350408277324583L;

	public FordRepairLabor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FordRepairLabor(Long vlid, String vbillId, String vroId,
			String vsstId, String vitemCode, String vitemName,
			Double nitemCost, Double nlaborHour, Double nlaborFee,
			Double ndiscountRate, String vnotes, String vcreated,
			Date dcrtDate, String vupdated, Date dupDate) {
		super(vlid, vbillId, vroId, vsstId, vitemCode, vitemName, nitemCost,
				nlaborHour, nlaborFee, ndiscountRate, vnotes, vcreated, dcrtDate,
				vupdated, dupDate);
		// TODO Auto-generated constructor stub
	}

}
