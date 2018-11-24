package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordRepairSalepartDao;
import com.jeecms.cms.entity.main.FordRepairSalepart;
import com.jeecms.cms.manager.main.FordRepairSalepartMng;
@Service
@Transactional
public class FordRepairSalepartMngImpl implements FordRepairSalepartMng {
	@Autowired
	private FordRepairSalepartDao dao;

	@Override
	public List<FordRepairSalepart> getFordRepairSalepartByBill(String bill) {
		// TODO Auto-generated method stub
		return this.dao.getFordRepairSalepartByBill(bill);
	}

}
