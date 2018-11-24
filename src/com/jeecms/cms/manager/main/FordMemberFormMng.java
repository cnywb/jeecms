package com.jeecms.cms.manager.main;

import java.util.List;

import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.FordMemberForm;

public interface FordMemberFormMng {

	public FordMemberForm getFordMemberFormBy(String vname,String vmobile,String vvin);
	
	public int authenticationCarOwner(CmsUser user,String vname,String vmobile,String vvin);
	
	public List<FordMemberForm> getListByVin(String vvin);
	
	public int authenManualy(CmsUser user,String vname,String vmobile,String vvin);
}
