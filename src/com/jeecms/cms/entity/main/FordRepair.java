package com.jeecms.cms.entity.main;

import java.util.Date;

import com.jeecms.cms.entity.main.base.BaseFordRepair;

public class FordRepair extends BaseFordRepair {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7135787651574717106L;

	public FordRepair() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FordRepair(String repairId, String vcarId, String vroId,
			String vbillId, String vsstId, String vsstName, String vvin,
			String vmodel, String vlicenseNmb, String vrepairDate,
			String vfinishDate, String vbillDate, String vbillStatus,
			String vrepairType, Long nmileage, Double nlaborFee,
			Double nrepairMaterialFee, Double nsaleMaterialFee,
			Double naddItemFee, Double noverItemFee, Double nrepairFee,
			Double nbalanceFee, String vnotes, String vcreated, Date dcrtDate,
			String vupdated, Date dupDate) {
		super(repairId, vcarId, vroId, vbillId, vsstId, vsstName, vvin, vmodel,
				vlicenseNmb, vrepairDate, vfinishDate, vbillDate, vbillStatus,
				vrepairType, nmileage, nlaborFee, nrepairMaterialFee, nsaleMaterialFee,
				naddItemFee, noverItemFee, nrepairFee, nbalanceFee, vnotes, vcreated,
				dcrtDate, vupdated, dupDate);
		// TODO Auto-generated constructor stub
	}

}
