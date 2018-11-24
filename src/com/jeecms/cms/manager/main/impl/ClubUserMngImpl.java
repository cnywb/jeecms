package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ClubUserDao;
import com.jeecms.cms.entity.main.ClubUser;
import com.jeecms.cms.manager.main.ClubUserMng;
@Service
@Transactional
public class ClubUserMngImpl implements ClubUserMng{
	
	@Autowired
	private ClubUserDao dao;

	@Override
	public boolean checkName(String uname) {
		// TODO Auto-generated method stub
		return dao.checkName(uname);
	}

	@Override
	public ClubUser save(ClubUser user) {
		// TODO Auto-generated method stub
		return dao.save(user);
	}

	@Override
	public ClubUser update(ClubUser user) {
		// TODO Auto-generated method stub
		return dao.update(user);
	}

	@Override
	public ClubUser findById(String uuid) {
		return dao.findById(uuid);
	}

}
