package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordRepairSalepartDao;
import com.jeecms.cms.entity.main.FordRepairSalepart;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordRepairSalepartDaoImpl extends HibernateBaseDao<FordRepairSalepart,Long> implements FordRepairSalepartDao {

	@Override
	public List<FordRepairSalepart> getFordRepairSalepartByBill(String bill) {
		Finder f = Finder.create("select f from FordRepairSalepart f where f.vbillId=:bill order by f.dcrtDate desc,f.vspid desc");
		f.setParam("bill", bill);
		return find(f);
	}

	@Override
	protected Class<FordRepairSalepart> getEntityClass() {
		// TODO Auto-generated method stub
		return FordRepairSalepart.class;
	}

}
