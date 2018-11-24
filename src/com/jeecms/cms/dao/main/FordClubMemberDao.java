package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordClubMember;

public interface FordClubMemberDao {
	
	public FordClubMember getFordClubMemberByVin(String vin);
	
	public String getSeq();
	
	public FordClubMember save(FordClubMember bean);
	
	public List<FordClubMember> getFordClubMemberByUid(Integer userId);
}
