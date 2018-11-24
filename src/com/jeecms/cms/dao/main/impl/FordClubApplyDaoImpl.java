package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordClubApplyDao;
import com.jeecms.cms.entity.main.FordClubApply;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordClubApplyDaoImpl extends HibernateBaseDao<FordClubApply, String> implements FordClubApplyDao{

	@Override
	protected Class<FordClubApply> getEntityClass() {
		return FordClubApply.class;
	}

	@Override
	public String getSeq() {
		String sql = "select to_char(SEQ_FORD_CLUB_APPLY.nextval,'000000000000000') from dual";	
		return (String)getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public FordClubApply save(FordClubApply bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public FordClubApply getFordClubApplyByVin(String vin) {
		Finder f = Finder.create("select bean from FordClubApply bean where upper(bean.vvin)=:vvin");
		f.setParam("vvin", vin.trim().toUpperCase());
		List<?> list = find(f);
		if(list!=null && list.size() > 0)
			return (FordClubApply)list.get(0);
		return null;
	}

	@Override
	public FordClubApply update(FordClubApply bean) {
		getSession().update(bean);
		return bean;
	}

}
