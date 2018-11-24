package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordClubApplyDao;
import com.jeecms.cms.entity.main.FordClubApply;
import com.jeecms.cms.manager.main.FordClubApplyMng;
@Service
@Transactional
public class FordClubApplyMngImpl implements FordClubApplyMng {
	
	@Autowired
	private FordClubApplyDao dao;

	@Override
	public String getSeq() {
		return dao.getSeq();
	}

	@Override
	public FordClubApply save(FordClubApply bean) {
		return dao.save(bean);
	}

	@Override
	public FordClubApply getFordClubApplyByVin(String vin) {
		return dao.getFordClubApplyByVin(vin);
	}

	@Override
	public FordClubApply update(FordClubApply bean) {
		return dao.update(bean);
	}

}
