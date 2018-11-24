package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordMemberFormDao;
import com.jeecms.cms.entity.main.FordMemberForm;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordMemberFormDaoImpl extends HibernateBaseDao<FordMemberForm, String> implements FordMemberFormDao {

	@Override
	protected Class<FordMemberForm> getEntityClass() {
		return FordMemberForm.class;
	}

	@Override
	public FordMemberForm getFordMemberFormBy(String vname, String vmobile,
			String vvin) {
		Finder f = Finder.create("select bean from FordMemberForm bean where bean.vformName=:vname and bean.vformTel=:vmobile and upper(bean.vvin)=:vvin ");
		f.setParam("vname", vname.trim());
		f.setParam("vmobile", vmobile.trim());
		f.setParam("vvin", vvin.trim().toUpperCase());
		List<?> list = find(f);
		if(list!=null && list.size()>0)
			return (FordMemberForm)list.get(0);
		return null;
	}

	@Override
	public List<FordMemberForm> getListByVin(String vvin) {
		Finder f = Finder.create("select bean from FordMemberForm bean where upper(bean.vvin)=:vvin ");
		f.setParam("vvin", vvin.trim().toUpperCase());
		return  find(f);
	}

}
