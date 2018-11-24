package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordClubMember;

public interface FordClubMemberMng {
	public FordClubMember getFordClubMemberByVin(String vin);
	public String getSeq();
	public FordClubMember save(FordClubMember bean);
	public List<FordClubMember> getFordClubMemberByUid(Integer userId);
}
