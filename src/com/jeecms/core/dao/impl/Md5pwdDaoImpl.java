package com.jeecms.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.core.dao.Md5pwdDao;
import com.jeecms.core.entity.Md5pwd;
@Repository
public class Md5pwdDaoImpl extends HibernateBaseDao<Md5pwd, Integer> implements Md5pwdDao{

	@Override
	public Md5pwd findById(int id) {
		return get(id);
	}

	@Override
	protected Class<Md5pwd> getEntityClass() {
		return Md5pwd.class;
	}

}
