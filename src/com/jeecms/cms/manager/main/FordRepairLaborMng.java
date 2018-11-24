package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairLabor;

public interface FordRepairLaborMng {
	public List<FordRepairLabor> getFordRepairLaborByBill(String bill);
}
