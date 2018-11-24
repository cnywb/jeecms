package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.ClubUser;

public interface ClubUserMng {
	public boolean checkName(String uname);
	
	public ClubUser save(ClubUser user);
	
	public ClubUser update(ClubUser user);
	
	public ClubUser findById(String uuid);
}
