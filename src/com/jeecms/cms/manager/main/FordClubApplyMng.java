package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.FordClubApply;

public interface FordClubApplyMng {
	
	public String getSeq();
	
	public FordClubApply save(FordClubApply bean);
	
	public FordClubApply getFordClubApplyByVin(String vin);
	
	public FordClubApply update(FordClubApply bean);
}
