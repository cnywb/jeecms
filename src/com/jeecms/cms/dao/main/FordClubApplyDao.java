package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.FordClubApply;

public interface FordClubApplyDao {

	public String getSeq();
	
	public FordClubApply save(FordClubApply bean);
	
	public FordClubApply getFordClubApplyByVin(String vin);
	
	public FordClubApply update(FordClubApply bean);
}
