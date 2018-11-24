package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.jeecms.cms.dao.main.FordRepairDao;
import com.jeecms.cms.entity.main.FordRepair;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordRepairDaoImpl extends HibernateBaseDao<FordRepair,String> implements FordRepairDao {

	@Override
	public FordRepair getFordRepairByBillId(String billId) {
		Finder f = Finder.create("select distinct f from FordRepair f where f.vbillId =:vbillId");
		f.setParam("vbillId", billId);
		List<FordRepair> list = find(f);
		if(null != list && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	protected Class<FordRepair> getEntityClass() {
		// TODO Auto-generated method stub
		return FordRepair.class;
	}

}
