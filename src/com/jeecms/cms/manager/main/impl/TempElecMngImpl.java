package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.TempElecDao;
import com.jeecms.cms.entity.main.TempElec;
import com.jeecms.cms.manager.main.TempElecMng;
@Service
@Transactional
public class TempElecMngImpl implements TempElecMng {
	@Autowired
	private TempElecDao dao;

	@Override
	public List<TempElec> getAllTempElecByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.dao.getAllTempElecByUserId(userId);
	}

	@Override
	public List<TempElec> getTempElecByTempDealId(Long tempDealId) {
		// TODO Auto-generated method stub
		return this.dao.getTempElecByTempDealId(tempDealId);
	}

}
