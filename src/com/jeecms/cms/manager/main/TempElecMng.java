package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.TempElec;

public interface TempElecMng {
	public List<TempElec> getAllTempElecByUserId(Integer userId);

	public List<TempElec> getTempElecByTempDealId(Long tempDealId);
}
