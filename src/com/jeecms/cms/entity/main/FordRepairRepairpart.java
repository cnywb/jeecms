package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseFordRepairRepairpart;

public class FordRepairRepairpart extends BaseFordRepairRepairpart {

	/**
	 * 
	 */
	private static final long serialVersionUID = -222987329783450431L;

	public FordRepairRepairpart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FordRepairRepairpart(Long vrpid, String vbillId, String vroId,
			String vsstId, String vpartCode, String vpartName, Long npartNum,
			String vchargePartition, String vmanageSort, String vismainPart,
			Long npartCostPrice, Long npartSalePrice, Long npartCostFee,
			Double npartSaleFee, Long ndiscountRate, String vnotes,
			String vcreated, Date dcrtDate, String vupdated, Date dupDate) {
		super(vrpid, vbillId, vroId, vsstId, vpartCode, vpartName, npartNum,
				vchargePartition, vmanageSort, vismainPart, npartCostPrice,
				npartSalePrice, npartCostFee, npartSaleFee, ndiscountRate, vnotes,
				vcreated, dcrtDate, vupdated, dupDate);
		// TODO Auto-generated constructor stub
	}

}
