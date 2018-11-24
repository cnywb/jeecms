package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordRepairRepairpartDao;
import com.jeecms.cms.entity.main.FordRepairRepairpart;
import com.jeecms.cms.manager.main.FordRepairRepairpartMng;
@Service
@Transactional
public class FordRepairRepairpartMngImpl implements FordRepairRepairpartMng {
	
	@Autowired
	private FordRepairRepairpartDao dao;

	@Override
	public List<FordRepairRepairpart> getFordRepairRepairpartByBill(String bill) {
		// TODO Auto-generated method stub
		return this.dao.getFordRepairRepairpartByBill(bill);
	}

}
