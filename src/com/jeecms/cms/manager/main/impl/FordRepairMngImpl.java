package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordRepairDao;
import com.jeecms.cms.entity.main.FordRepair;
import com.jeecms.cms.manager.main.FordRepairMng;
@Service
@Transactional
public class FordRepairMngImpl implements FordRepairMng {
	
	@Autowired
	private FordRepairDao dao;

	@Override
	public FordRepair getFordRepairByBillId(String billId) {
		// TODO Auto-generated method stub
		return this.dao.getFordRepairByBillId(billId);
	}

}
