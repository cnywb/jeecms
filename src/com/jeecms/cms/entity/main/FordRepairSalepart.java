package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseFordRepairSalepart;

public class FordRepairSalepart extends BaseFordRepairSalepart {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6294908622822274430L;

	public FordRepairSalepart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FordRepairSalepart(Long vspid, String vbillId, String vroId,
			String vsstId, String vpartCode, String vpartName, Long npartNum,
			String vchargePartition, String vmanageSort, Long npartCostPrice,
			Long npartSalePrice, Long npartCostFee, Long npartSaleFee,
			Long ndiscountRate, String vnotes, String vcreated, Date dcrtDate,
			String vupdated, Date dupDate) {
		super(vspid, vbillId, vroId, vsstId, vpartCode, vpartName, npartNum,
				vchargePartition, vmanageSort, npartCostPrice, npartSalePrice,
				npartCostFee, npartSaleFee, ndiscountRate, vnotes, vcreated, dcrtDate,
				vupdated, dupDate);
		// TODO Auto-generated constructor stub
	}
	
	

}
