package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordRepairRepairpart;


public interface FordRepairRepairpartDao {
	public List<FordRepairRepairpart> getFordRepairRepairpartByBill(String bill);
}
