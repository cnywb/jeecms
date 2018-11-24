package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordCar;

public interface FordCarDao {

	public FordCar findById(String vcarId);
	
	public List<FordCar> findByVin(String vvin);
}
