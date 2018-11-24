package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.TempDeal;

public interface TempDealMng {
	public List<TempDeal> getTop3TempDealByUserId (Integer uuid,int count);
	
	public List<TempDeal> getFailureTempDeal(Integer uuid);
	
	public List<TempDeal> getUsableTempDeal(Integer uuid);
	
	public TempDeal findById(Long id);
	
	public List<TempDeal> getTempDealsByVin(String vin,int count);
}
