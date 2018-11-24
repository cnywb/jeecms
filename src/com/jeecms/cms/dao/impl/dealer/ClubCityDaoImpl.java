package com.jeecms.cms.dao.impl.dealer;

import java.util.List;

import com.jeecms.cms.dao.dealer.ClubCityDao;
import com.jeecms.cms.entity.main.ClubCity;
import com.jeecms.cms.entity.main.ClubProvince;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class ClubCityDaoImpl extends HibernateBaseDao<ClubCity, Long> implements
		ClubCityDao {

	@Override
	protected Class<ClubCity> getEntityClass() {
		// TODO Auto-generated method stub
		return ClubCity.class;
	}
	
	
	public List<ClubCity> findAllByProvinceId(long provinceId) {
		String hql = "select bean from ClubCity bean where bean.provinceId=?";
		List<ClubCity> list=find(hql,provinceId);
		return list;
	}

}
