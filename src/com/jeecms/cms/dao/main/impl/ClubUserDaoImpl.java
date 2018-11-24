package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ClubUserDao;
import com.jeecms.cms.entity.main.ClubUser;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class ClubUserDaoImpl extends HibernateBaseDao<ClubUser, String> implements ClubUserDao{

	@Override
	protected Class<ClubUser> getEntityClass() {
		return ClubUser.class;
	}

	@Override
	public boolean checkName(String uname) {
		Finder f = Finder.create("select u from ClubUser u where u.uname=:uname");
		f.setParam("uname", uname.trim());
		List<?> list = find(f);
		if(null != list && list.size() > 0)
			return true;
		return false;
	}

	@Override
	public ClubUser save(ClubUser user) {
		getSession().save(user);
		return user;
	}

	@Override
	public ClubUser update(ClubUser user) {
		getSession().update(user);
		return user;
	}

	@Override
	public ClubUser findById(String uuid) {
		return get(uuid);
	}

}
