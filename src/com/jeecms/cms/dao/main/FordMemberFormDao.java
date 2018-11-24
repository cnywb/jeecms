package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.FordMemberForm;

public interface FordMemberFormDao {

	public FordMemberForm getFordMemberFormBy(String vname,String vmobile,String vvin);
	
	public List<FordMemberForm> getListByVin(String vvin);
}
