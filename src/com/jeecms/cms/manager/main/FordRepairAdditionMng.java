package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairAddition;

public interface FordRepairAdditionMng {
	public List<FordRepairAddition> getFordRepairAdditionByBill(String bill);
}
