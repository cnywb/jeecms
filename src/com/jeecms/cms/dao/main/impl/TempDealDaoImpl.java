package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.TempDealDao;
import com.jeecms.cms.entity.main.TempDeal;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class TempDealDaoImpl extends HibernateBaseDao<TempDeal,Long> implements TempDealDao {

	@Override
	public List<TempDeal> getTop3TempDealByUserId(Integer uuid,int count) {
		Finder f = Finder.create("select b from TempDeal b where b.vin in(select distinct f.vvin from FordClubMember f where f.userId=:userId) order by b.validBeginDate desc");
		f.setParam("userId", uuid);
		f.setMaxResults(count);
		return find(f);
	}

	@Override
	public List<TempDeal> getFailureTempDeal(Integer uuid) {
		Finder f = Finder.create("select b from TempDeal b where b.vin in(select distinct f.vvin from FordClubMember f where f.userId=:userId) and (to_date(b.validEndDate, 'yyyy-mm-dd') < sysdate or ((b.amount - b.useAmount) <= 0)) order by b.validBeginDate desc ");
		f.setParam("userId", uuid);
		return  find(f);
		
	}

	@Override
	public List<TempDeal> getUsableTempDeal(Integer uuid) {
		Finder f = Finder.create("select b from TempDeal b where b.vin in(select distinct f.vvin from FordClubMember f where f.userId=:userId) and (to_date(b.validEndDate, 'yyyy-mm-dd') >= sysdate and ((b.amount - b.useAmount) > 0)) order by b.validBeginDate desc ");
		f.setParam("userId", uuid);
		return  find(f);
	}

	@Override
	protected Class<TempDeal> getEntityClass() {
		// TODO Auto-generated method stub
		return TempDeal.class;
	}

	@Override
	public TempDeal findById(Long id) {
		return get(id);
	}

	@Override
	public List<TempDeal> getTempDealsByVin(String vin,int count) {
		Finder f = Finder.create("select b from TempDeal b where upper(b.vin)=:vin order by b.validBeginDate desc");
		f.setParam("vin", vin.trim().toUpperCase());
		if(count > 0)
			f.setMaxResults(count);
		return find(f);
	}

}
