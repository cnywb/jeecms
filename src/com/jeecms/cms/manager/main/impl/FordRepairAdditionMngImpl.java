package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordRepairAdditionDao;
import com.jeecms.cms.entity.main.FordRepairAddition;
import com.jeecms.cms.manager.main.FordRepairAdditionMng;
@Service
@Transactional
public class FordRepairAdditionMngImpl implements FordRepairAdditionMng {
	
	@Autowired
	private FordRepairAdditionDao dao;

	@Override
	public List<FordRepairAddition> getFordRepairAdditionByBill(String bill) {
		// TODO Auto-generated method stub
		return this.dao.getFordRepairAdditionByBill(bill);
	}

}
