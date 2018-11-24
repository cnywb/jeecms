package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.TempElec;


public interface TempElecDao {
	
	public List<TempElec> getAllTempElecByUserId(Integer userId); 
	
	public List<TempElec> getTempElecByTempDealId(Long tempDealId);

}
