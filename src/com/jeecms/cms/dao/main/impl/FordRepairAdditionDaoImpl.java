package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordRepairAdditionDao;
import com.jeecms.cms.entity.main.FordRepairAddition;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordRepairAdditionDaoImpl extends HibernateBaseDao<FordRepairAddition,Long> implements FordRepairAdditionDao {

	@Override
	public List<FordRepairAddition> getFordRepairAdditionByBill(String bill) {
		Finder f = Finder.create("select f from FordRepairAddition f where f.vbillId = :bill");
		f.setParam("bill", bill);
		return find(f);
	}

	@Override
	protected Class<FordRepairAddition> getEntityClass() {
		// TODO Auto-generated method stub
		return FordRepairAddition.class;
	}

}
