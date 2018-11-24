package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordRepairRepairpartDao;
import com.jeecms.cms.entity.main.FordRepairRepairpart;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordRepairRepairpartDaoImpl extends HibernateBaseDao<FordRepairRepairpart,Long> implements FordRepairRepairpartDao {

	@Override
	public List<FordRepairRepairpart> getFordRepairRepairpartByBill(String bill) {
		Finder f = Finder.create("select f from FordRepairRepairpart f where f.vbillId=:bill order by f.dcrtDate desc,f.vrpid desc");
		f.setParam("bill", bill);
		return find(f);
	}

	@Override
	protected Class<FordRepairRepairpart> getEntityClass() {
		// TODO Auto-generated method stub
		return FordRepairRepairpart.class;
	}

}
