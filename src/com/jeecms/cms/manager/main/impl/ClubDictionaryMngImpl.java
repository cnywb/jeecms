package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ClubDictionaryDao;
import com.jeecms.cms.entity.main.ClubDictionary;
import com.jeecms.cms.manager.main.ClubDictionaryMng;
@Service
@Transactional
public class ClubDictionaryMngImpl implements ClubDictionaryMng {
	
	@Autowired
	private ClubDictionaryDao dao;

	@Override
	public ClubDictionary getClubDictionaryByCode(String cdcode) {
		// TODO Auto-generated method stub
		return this.dao.getClubDictionaryByCode(cdcode);
	}

}
