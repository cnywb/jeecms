package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordCarDao;
import com.jeecms.cms.entity.main.FordCar;
import com.jeecms.cms.manager.main.FordCarMng;
@Service
@Transactional
public class FordCarMngImpl implements FordCarMng {
	
	@Autowired
	private FordCarDao dao;

	@Override
	public FordCar findById(String vcarId) {
		// TODO Auto-generated method stub
		return this.dao.findById(vcarId);
	}

	@Override
	public List<FordCar> findByVin(String vvin) {
		// TODO Auto-generated method stub
		return this.dao.findByVin(vvin);
	}

	@Override
	public FordCar findByVinOfOne(String vvin) {
		List<FordCar> list =  findByVin(vvin);
		if(null !=list && list.size() > 0)
			return list.get(0);
		return null;
	}

}
