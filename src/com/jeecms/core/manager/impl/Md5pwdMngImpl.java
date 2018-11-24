package com.jeecms.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.core.dao.Md5pwdDao;
import com.jeecms.core.entity.Md5pwd;
import com.jeecms.core.manager.Md5pwdMng;
@Service
@Transactional
public class Md5pwdMngImpl implements Md5pwdMng{

	@Override
	public Md5pwd findById(int id) {
		return this.dao.findById(id);
	}

	@Autowired
	private Md5pwdDao dao;
}
