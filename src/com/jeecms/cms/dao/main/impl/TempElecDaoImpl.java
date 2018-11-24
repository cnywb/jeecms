package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.TempElecDao;
import com.jeecms.cms.entity.main.TempElec;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class TempElecDaoImpl extends HibernateBaseDao<TempElec,String> implements TempElecDao {

	@Override
	public List<TempElec> getAllTempElecByUserId(Integer userId) {
		Finder f = Finder.create(" select b from TempElec b where b.accountId in (select distinct t.id from TempDeal t where t.vin in (select distinct f.vvin from FordClubMember f where f.userId=:userId)) order by b.usedDate desc");
		f.setParam("userId", userId);
		return find(f);
	}

	@Override
	public List<TempElec> getTempElecByTempDealId(Long tempDealId) {
		Finder f = Finder.create(" select b from TempElec b where b.accountId in (select distinct t.id from TempDeal t where t.id=:tempDealId) order by b.usedDate desc");
		f.setParam("tempDealId", tempDealId);
		return find(f);
	}

	@Override
	protected Class<TempElec> getEntityClass() {
		// TODO Auto-generated method stub
		return TempElec.class;
	}

}
