package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.TempDealDao;
import com.jeecms.cms.entity.main.TempDeal;
import com.jeecms.cms.manager.main.TempDealMng;
@Service
@Transactional
public class TempDealMngImpl implements TempDealMng {
	@Autowired
	private TempDealDao dao;

	@Override
	public List<TempDeal> getTop3TempDealByUserId(Integer uuid, int count) {
		// TODO Auto-generated method stub
		return this.dao.getTop3TempDealByUserId(uuid, count);
	}

	@Override
	public List<TempDeal> getFailureTempDeal(Integer uuid) {
		// TODO Auto-generated method stub
		return this.dao.getFailureTempDeal(uuid);
	}

	@Override
	public List<TempDeal> getUsableTempDeal(Integer uuid) {
		// TODO Auto-generated method stub
		return this.dao.getUsableTempDeal(uuid);
	}

	@Override
	public TempDeal findById(Long id) {
		// TODO Auto-generated method stub
		return this.dao.findById(id);
	}

	@Override
	public List<TempDeal> getTempDealsByVin(String vin, int count) {
		return this.dao.getTempDealsByVin(vin, count);
	}

}
