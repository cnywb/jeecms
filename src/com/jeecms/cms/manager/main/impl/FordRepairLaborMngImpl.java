package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordRepairLaborDao;
import com.jeecms.cms.entity.main.FordRepairLabor;
import com.jeecms.cms.manager.main.FordRepairLaborMng;
@Service
@Transactional
public class FordRepairLaborMngImpl implements FordRepairLaborMng {
	
	@Autowired
	private FordRepairLaborDao dao;

	@Override
	public List<FordRepairLabor> getFordRepairLaborByBill(String bill) {
		// TODO Auto-generated method stub
		return this.dao.getFordRepairLaborByBill(bill);
	}

}
