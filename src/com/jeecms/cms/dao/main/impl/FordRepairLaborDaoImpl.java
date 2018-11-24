package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordRepairLaborDao;
import com.jeecms.cms.entity.main.FordRepairLabor;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordRepairLaborDaoImpl extends HibernateBaseDao<FordRepairLabor,Long> implements FordRepairLaborDao {

	@Override
	public List<FordRepairLabor> getFordRepairLaborByBill(String bill) {
		Finder f = Finder.create("select f from FordRepairLabor f where f.vbillId=:bill order by f.dcrtDate desc,f.vlid desc");
		f.setParam("bill", bill);
		return find(f);
	}

	@Override
	protected Class<FordRepairLabor> getEntityClass() {
		// TODO Auto-generated method stub
		return FordRepairLabor.class;
	}

}
