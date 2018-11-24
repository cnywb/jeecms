package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordClubMemberDao;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordClubMemberDaoImpl extends HibernateBaseDao<FordClubMember, String> implements FordClubMemberDao {

	@Override
	protected Class<FordClubMember> getEntityClass() {
		return FordClubMember.class;
	}

	@Override
	public FordClubMember getFordClubMemberByVin(String vin) {
		Finder f = Finder.create("select m from FordClubMember m where upper(m.vvin)=:vin");
		f.setParam("vin", vin.trim().toUpperCase());
		List<?> list = find(f);
		if(list!=null && list.size()>0)
			return (FordClubMember)list.get(0);
		return null;
	}

	@Override
	public FordClubMember save(FordClubMember bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public String getSeq() {
		String sql = "select to_char(SEQ_FORD_CLUB_MEMBER.nextval,'0000000000') from dual";	
		return (String)getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public List<FordClubMember> getFordClubMemberByUid(Integer userId) {
		Finder f = Finder.create("select m from FordClubMember m where m.userId=:userId");
		f.setParam("userId", userId);
		return find(f);
	}

}
