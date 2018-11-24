package com.jeecms.cms.dao.impl.dealer;

import java.util.List;

import com.jeecms.cms.dao.dealer.ClubProvinceDao;
import com.jeecms.cms.entity.main.ClubProvince;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class ClubProvinceDaoImpl extends HibernateBaseDao<ClubProvince, Long> implements ClubProvinceDao{

	@Override
	protected Class<ClubProvince> getEntityClass() {
		return ClubProvince.class;
	}
	
	
	public List<ClubProvince> findAll() {
		String hql = "select bean from ClubProvince bean";
		List<ClubProvince> list=find(hql,null);
		return list;
	}

}
