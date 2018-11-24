package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairLabor;


public interface FordRepairLaborDao {
	public List<FordRepairLabor> getFordRepairLaborByBill(String bill);
}
