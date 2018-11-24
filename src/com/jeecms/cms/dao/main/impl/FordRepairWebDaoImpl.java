package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordRepairWebDao;
import com.jeecms.cms.entity.main.FordRepairWeb;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
@Repository
public class FordRepairWebDaoImpl extends HibernateBaseDao<FordRepairWeb, String> implements FordRepairWebDao {

	@Override
	public Pagination getFordRepairWebByUserId(Integer userId,int pageNo, int pageSize) {
		Finder f = Finder.create("select f from FordRepairWeb f where f.vvin in(select  distinct fc.vvin from FordClubMember fc where fc.userId=:userId) order by f.dcrtDate desc,f.vroId desc");
		f.setParam("userId", userId);
		return find(f,pageNo,pageSize);
	}

	@Override
	protected Class<FordRepairWeb> getEntityClass() {
		return FordRepairWeb.class;
	}

	@Override
	public List<FordRepairWeb> getFordRepairWebByUserId(Integer userId,
			int count) {
		Finder f = Finder.create("select f from FordRepairWeb f where f.vvin in(select  distinct fc.vvin from FordClubMember fc where fc.userId=:userId) order by f.dcrtDate desc,f.vroId desc");
		f.setParam("userId", userId);
		if(count>0)
			f.setMaxResults(count);
		return find(f);
	}

	@Override
	public List<FordRepairWeb> getFordRepairWebByCardId(String vvin, int count) {
		Finder f = Finder.create("select f from FordRepairWeb f where f.vvin=:vvin order by f.dcrtDate desc,f.vroId desc");
		f.setParam("vvin", vvin);
		if(count>0)
			f.setMaxResults(count);
		return find(f);
	}

}
