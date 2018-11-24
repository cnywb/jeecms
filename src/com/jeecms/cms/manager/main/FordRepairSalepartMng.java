package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairSalepart;

public interface FordRepairSalepartMng {
	public List<FordRepairSalepart> getFordRepairSalepartByBill(String bill);
}
