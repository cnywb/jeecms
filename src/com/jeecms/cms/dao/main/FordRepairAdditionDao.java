package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairAddition;


public interface FordRepairAdditionDao {
	public List<FordRepairAddition> getFordRepairAdditionByBill(String bill);
}
