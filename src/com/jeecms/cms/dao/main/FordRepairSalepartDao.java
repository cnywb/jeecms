package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairSalepart;


public interface FordRepairSalepartDao {
	public List<FordRepairSalepart> getFordRepairSalepartByBill(String bill);
}
