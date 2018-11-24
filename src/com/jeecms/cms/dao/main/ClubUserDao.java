package com.jeecms.cms.dao.main;

import com.jeecms.cms.entity.main.ClubUser;

public interface ClubUserDao {
	
	public boolean checkName(String uname);
	
	public ClubUser save(ClubUser user);
	
	public ClubUser update(ClubUser user);
	
	public ClubUser findById(String uuid);
}
