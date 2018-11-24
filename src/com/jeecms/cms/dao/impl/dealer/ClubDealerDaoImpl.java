package com.jeecms.cms.dao.impl.dealer;

import java.util.List;

import com.jeecms.cms.dao.dealer.ClubDealerDao;
import com.jeecms.cms.entity.main.ClubDealer;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class ClubDealerDaoImpl extends HibernateBaseDao<ClubDealer, Long>
		implements ClubDealerDao {

	@Override
	protected Class<ClubDealer> getEntityClass() {
		// TODO Auto-generated method stub
		return ClubDealer.class;
	}

	
	public List<ClubDealer> findAllByCityId(long cityId) {
		String hql = "select bean from ClubDealer bean where bean.cityId=?";
		List<ClubDealer> list=find(hql,cityId);
		return list;
	}
}
