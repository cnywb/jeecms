package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordCar;

public interface FordCarMng {
	public FordCar findById(String vcarId);
	public List<FordCar> findByVin(String vvin);
	public FordCar findByVinOfOne(String vvin);
}
